package com.alberoframework.core.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alberoframework.core.string.Strings;
import com.google.common.base.Objects;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

//TODO: Move to base utils.
public class Reflection {

	public static <T> T newInstance(Class<T> clazz) {
		T obj;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Cannot create object of class " + clazz.getName(), e);
		}
		return obj;
	}
	
	public static <T> T newInstance(Class<T> clazz, List<Object> dependencies) {

		T object = null;
		
		for (Constructor<?> constructor : clazz.getConstructors()) {

			if (constructor.getParameterTypes().length != dependencies.size()) {
				continue;
			}
			
			List<Object> constructorParameters = new ArrayList<Object>();
			
			for (Class<?> parameterType : constructor.getParameterTypes()) {
				for (Object dependency : dependencies) {
					if (parameterType.isAssignableFrom(dependency.getClass())) {
						constructorParameters.add(dependency);
						break;
					}
				}
			}
			
			if (constructorParameters.size() != dependencies.size())
				continue;
			
			if (object != null) {
				throw new IllegalArgumentException("Cannot create object of class " + clazz.getName() + " the dependencies match more than one constructor");
			}
			
			try {
				object = (T) constructor.newInstance(constructorParameters.toArray());
			}
			catch (Exception e) {
				throw new RuntimeException("Error initializing CommandHandlerTestContext while creating the command handler instance", e);
			}
			
		}
		
		if (object == null) {
			throw new IllegalArgumentException("Cannot create object of class " + clazz.getName() + " the dependencies dont match any constructor");
		}
		
		return object;
		
	}
	
	public static <T> void injectDependencies(T object, Map<String, Object> dependencies) {
		Method[] methods = object.getClass().getMethods(); //TODO: Get also private methods
		for (Map.Entry<String, Object> dependency : dependencies.entrySet()) {
			String setterName = "set" + Strings.upperCaseFirstLetter(dependency.getKey());
//			Method setterMethod = null;
			for (Method method : methods) {
				if (Objects.equal(method.getName(), setterName) 
						&& method.getParameterCount() == 1
						&& method.getParameters()[0].getType().isAssignableFrom(dependency.getValue().getClass())) {
//					setterMethod = method;
					try {
						method.setAccessible(true);
						method.invoke(object, dependency.getValue());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new RuntimeException("Cannot inject dependency " + dependency.getKey() + " on object " + object, e);
					}
					break;
				}
			}
//			if (setterMethod != null) {
//				setterMethod 
//			}
		}
		
	}
	
	public static Class<?>[] resolveGenericParameters(Class<?> clazz) {
		
		Type classType = clazz.getGenericSuperclass();
		
		ParameterizedType parameterizedType = (ParameterizedType) classType;
		Class<?>[] result = new Class<?>[parameterizedType.getActualTypeArguments().length];
		int i=0;
		for (Type type : parameterizedType.getActualTypeArguments()) {
			if (type instanceof Class) {
				result[i++] = (Class<?>) type;
			}
			if (type instanceof ParameterizedTypeImpl) {
				result[i++] = ((ParameterizedTypeImpl)type).getRawType();
			}
		}
		return result;
	}
	
	public static Class<?> resolveGenericParameter(Class<?> clazz) {
		return resolveGenericParameters(clazz)[0];
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> resolveFirstGenericParameterOfType(Class<?> clazz, Class<T> type) {
		Class<?>[] parameters = resolveGenericParameters(clazz);
		for (Class<?> parameter : parameters) {
			if (type.isAssignableFrom(parameter)) {
				return (Class<? extends T>) parameter;
			}
		}
		return null;
	}
	
}
