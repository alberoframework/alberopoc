package com.alberoframework.component.url;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.url.SpringRequestUrlMapper;
import com.alberoframework.component.url.MultipartContentBuilder.MultipartServletInputStream;
import com.alberoframework.content.type.BinaryContent;
import com.alberoframework.lang.object.BaseObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

public class SpringRequestUrlMapperTest {

    SpringRequestUrlMapper mappingService;

    @Before
    public void setUp() {
        mappingService = new SpringRequestUrlMapper(new ObjectMapper(), "http://localhost:8080");
    }

    @Test
    public void testResolveAQuery() throws IOException {
        mappingService.registerQuery("/myquery/{id}", UMSTQuery.class);

        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("GET");
        when(request.getParameterMap()).thenReturn(ImmutableMap.of("number", new String[]{ "2" }));
        when(request.getRequestURI()).thenReturn("/myquery/the-id");

        final Request<?> query = mappingService.resolveRequest(request);

        assertEquals(
            new UMSTQuery("the-id", null, 2, null),
            query
        );
    }
    @Test
    public void testResolveAQueryWithConflictingHandler() throws IOException {
        mappingService.registerQuery("/myquery/{id}", UMSTQuery.class);
        mappingService.registerQuery("/myquery/{message}/echo", EchoQuery.class);

        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/myquery/hello-world/echo");

        final Request<?> query = mappingService.resolveRequest(request);

        assertEquals(
            new EchoQuery("hello-world"),
            query
        );
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRequestDoesntMatch() throws IOException {
        mappingService.registerQuery("/myquery/{id}", UMSTQuery.class);

        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestURI()).thenReturn("/myquery/the-id");

        mappingService.resolveRequest(request);
    }
    @Test
    public void testCreateLinkForQuery() {
        mappingService.registerQuery("/myquery/{id}/query", UMSTQuery.class);

        assertEquals(
            "http://localhost:8080/myquery/anId/query?number=5&test=pi%20zz%C3%A0",
            mappingService.resolveUrl(new UMSTQuery("anId", "pi zzà", 5, null))
        );
    }
    @Test
    public void testUploadABinaryContent() throws IOException {
        final String contentType = "plain/text";
        final byte[] content = "test".getBytes();

        mappingService.registerCommand("/test/{id}", BinaryContentMessage.class);

        final MultipartServletInputStream body = MultipartContentBuilder.multipart()
            .withParam("text", "value")
            .withContent("binary", "file.txt", "test".getBytes(), "plain/text")
            .inputStream();

        final HttpServletRequest request = mockPost(body.contentType(), "/test/1", body);

        assertEquals(
            new BinaryContentMessage(1, "value", new BinaryContent(content, contentType)),
            mappingService.resolveRequest(request)
        );
    }

    @Test
    public void testUploadABinaryContentMissingPropertyIgnored() throws IOException {
        final String contentType = "plain/text";
        final byte[] content = "test".getBytes();

        mappingService.registerCommand("/test/{id}", BinaryContentMessage.class);

        final MultipartServletInputStream body = MultipartContentBuilder.multipart()
            .withParam("text", "value")
            .withParam("wrong", "wrong")
            .withContent("binary", "file.txt", content, contentType)
            .inputStream();

        final HttpServletRequest request = mockPost(body.contentType(), "/test/1", body);

        assertEquals(
            new BinaryContentMessage(1, "value", new BinaryContent(content, contentType)),
            mappingService.resolveRequest(request)
        );
    }

    @Test
    public void testResolveACommand() throws Exception {
        mappingService.registerCommand("/commands/{id}/post", UMSTCommand.class);

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("{\"name\": \"the-test\"}".getBytes());
        final HttpServletRequest request = mockPost("application/json", "/commands/theId/post", new MockServletInputStream(byteArrayInputStream));

        assertEquals(
            new UMSTCommand("theId", "the-test"),
            mappingService.resolveRequest(request)
        );
    }

    @Test
    public void testCreateLinkForCommand() throws Exception {
        mappingService.registerCommand("/commands/{id}", UMSTCommand.class);

        assertEquals(
            "http://localhost:8080/commands/theId",
            mappingService.resolveUrl(new UMSTCommand("theId", "the-test"))
        );
    }
    
    public static class UMSTQuery extends BaseObject implements Query {
        private String id;
        private String test;
        private Integer number;
        private String test2;
        
		public UMSTQuery(String id, String test, Integer number, String test2) {
			this.id = id;
			this.test = test;
			this.number = number;
			this.test2 = test2;
		}

		public UMSTQuery() {
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTest() {
			return test;
		}

		public void setTest(String test) {
			this.test = test;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}

		public String getTest2() {
			return test2;
		}

		public void setTest2(String test2) {
			this.test2 = test2;
		}
        
    }
    
    public static class EchoQuery extends BaseObject implements Query<String> {
        private String message;

		public EchoQuery(String message) {
			this.message = message;
		}

		public EchoQuery() {
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
        
    }
    
    public static class UMSTCommand extends BaseObject implements Command {
        private String id;
        private String name;
        
		public UMSTCommand(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public UMSTCommand() {
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

    }
    
    public static class BinaryContentMessage extends BaseObject implements Command{
        private Integer id;
        private String text;
        private BinaryContent binary;
        
		public BinaryContentMessage(Integer id, String text, BinaryContent binary) {
			this.id = id;
			this.text = text;
			this.binary = binary;
		}

		public BinaryContentMessage() {
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public BinaryContent getBinary() {
			return binary;
		}

		public void setBinary(BinaryContent binary) {
			this.binary = binary;
		}

    }
    
    private static class MockServletInputStream extends ServletInputStream {

        private InputStream forward;
        private boolean ready = true;
        
        public MockServletInputStream(InputStream forward) {
			this.forward = forward;
		}

		@Override
        public int read() throws IOException {
        	int value = forward.read();
        	if (value == -1)
        		ready = false;
            return value;
        }
        
        @Override
        public int available() throws IOException {
            return forward.available();
        }
        
		@Override
		public boolean isFinished() {
			return !ready;
		}
		@Override
		public boolean isReady() {
			return ready;
		}
		@Override
		public void setReadListener(ReadListener listener) {
		}

    }

    private static HttpServletRequest mockPost(String contentType, String uri, InputStream body) throws IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getContentType()).thenReturn(contentType);
        when(request.getContentLength()).thenReturn(body.available());
        when(request.getInputStream()).thenReturn(new MockServletInputStream(body));
        when(request.getRequestURI()).thenReturn(uri);
        when(request.getMethod()).thenReturn("POST");
        return request;
    }
}
