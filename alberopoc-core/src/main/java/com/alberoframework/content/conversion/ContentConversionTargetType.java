package com.alberoframework.content.conversion;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.alberoframework.core.validation.Validation;

//TODO: Is this the best way? this is identical to jackson's TypeReference

public abstract class ContentConversionTargetType<T> {
	
	protected final Type type;
    
    protected ContentConversionTargetType()
    {
        Type superClass = getClass().getGenericSuperclass();
        
        Validation.validate(!(superClass instanceof Class<?>), IllegalArgumentException::new, "Conversion Target Type extended without specifying the generic type");
        
        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() { 
    	return type; 
    }
	
}
