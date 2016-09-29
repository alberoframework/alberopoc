package com.alberoframework.testing.bdd.junit;

import java.util.List;

import org.junit.Assert;
import org.junit.internal.runners.model.ReflectiveCallable;
import org.junit.internal.runners.statements.Fail;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.support.AbstractTestSupport.TestSpecification;

public class BddJUnitTestRunner extends BlockJUnit4ClassRunner {

	public BddJUnitTestRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}  

	@Override 
	@SuppressWarnings("rawtypes")
	protected Statement methodBlock(final FrameworkMethod method) { 
			final Object test;
	    try {
	        test = new ReflectiveCallable() {
	            @Override
	            protected Object runReflectiveCall() throws Throwable {
	                return createTest();
	            }
	        }.run();
	    } catch (Throwable e) {
	        return new Fail(e);
	    }

//      return new Statement() {  
//          @Override  
//          public void evaluate() throws Throwable {  
//          	  Object result = method.invokeExplosively(test);
//          	  if (result != null && result instanceof TestOutcome) {
//          	  		Assert.assertTrue(result.toString(), ((TestOutcome) result).successful());
//          	  }
//          }         
//      };  
      
      return new Statement() {  
          @Override  
          public void evaluate() throws Throwable {  
          	  Object methodReturnValue = method.invokeExplosively(test);
          	  if (methodReturnValue != null && methodReturnValue instanceof TestSpecification) {
					TestOutcome outcome = ((TestSpecification) methodReturnValue).run();
          	  		Assert.assertTrue(outcome.toString(), outcome.successful());
          	  }
          	  else {
          		  Assert.fail("Test methods must return a Test Specification");
          	  }
          }         
      }; 
  }
	
	@Override
	protected void validateTestMethods(List<Throwable> errors) {
		//Do nothing for now, but you could use this to validate that every method returns a TestOutcome object ... however you have to override this because BlockRunner checks that every method is void
	}  
	
	
	
}
