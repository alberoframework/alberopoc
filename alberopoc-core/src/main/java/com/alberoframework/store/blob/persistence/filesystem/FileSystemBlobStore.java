package com.alberoframework.store.blob.persistence.filesystem;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.commons.io.IOUtils;

import com.alberoframework.store.blob.contract.BlobId;
import com.alberoframework.store.blob.contract.BlobStore;

public class FileSystemBlobStore implements BlobStore {
	
	private final Path basePath;

	public FileSystemBlobStore(String basePath) {
		this(Paths.get(basePath));
	}
	
    public FileSystemBlobStore(Path basePath) {
        if (!basePath.toFile().exists()) {
            if (!basePath.toFile().mkdirs()) {
                throw new RuntimeException("Can't create during initialization" + basePath.toFile().getAbsolutePath());
            }
        }
        this.basePath = basePath;
    }
    
    @Override
    public void copy(BlobId from, BlobId to) {
    	final byte[] sourceContent = retrieve(from).orElseThrow(() -> new IllegalArgumentException("Can't find file " + from.getName()));
        store(to, sourceContent);
    }

    @Override
    public void store(BlobId blobId, byte[] content) {
    	OutputStream os = outputStream(blobId);
        try {
            IOUtils.copy(new ByteArrayInputStream(content), os);
        }
        catch (IOException e) {
            throw new RuntimeException("Can't copy streams " + blobId.getName(), e);
        }
    }
    
    @Override
    public Optional<byte[]> retrieve(BlobId blobId) {
        final File f = toFile(blobId);
        if (!f.exists()) {
            return Optional.empty();
        }
        try (InputStream newInputStream = new FileInputStream(f)) {
            return Optional.of(IOUtils.toByteArray(newInputStream));
        } catch (IOException e) {
            throw new IllegalStateException("Cant retrieve input stream " + blobId.getName(), e);
        }
    }
    
    
    private OutputStream outputStream(BlobId blobId) {
        final File outFile = toFile(blobId);
        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }
        try {
            OutputStream res = new FileOutputStream(outFile);
            return res;
        } catch (IOException e) {
            throw new RuntimeException("Can't open output stream " + blobId.getName(), e);
        }
    }
    
    //utilities
    
    private File toFile(BlobId blobId) {
        return toPath(getBasePath(), blobId).toFile();
    }
	
    private Path toPath(Path root, BlobId blobId) {
        return getBasePath().resolve(blobId.getPartition().joinPartsBy("/") + "/" + blobId.getName());
    }
    
    public Path getBasePath() {
		return basePath;
	}
}
