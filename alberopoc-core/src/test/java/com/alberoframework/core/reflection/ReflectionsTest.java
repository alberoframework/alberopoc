package com.alberoframework.core.reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alberoframework.core.reflection.Reflection;
import com.alberoframework.core.reflection.ReflectionsTestStubs.ConcreteParameterizedType;
import com.alberoframework.core.reflection.ReflectionsTestStubs.ConcreteParameterizedWithSubPararameterizedType;
import com.alberoframework.core.reflection.ReflectionsTestStubs.Dependency1;
import com.alberoframework.core.reflection.ReflectionsTestStubs.Dependency2;
import com.alberoframework.core.reflection.ReflectionsTestStubs.Dependency3;
import com.alberoframework.core.reflection.ReflectionsTestStubs.ObjectWithTwoDependencies;
import com.alberoframework.core.reflection.ReflectionsTestStubs.ObjectWithTwoDependenciesAndAmbiguousConstructors;
import com.alberoframework.core.reflection.ReflectionsTestStubs.ObjectWithTwoDependenciesButOneSetter;
import com.alberoframework.core.reflection.ReflectionsTestStubs.ObjectWithTwoPrivateSetterDependencies;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;

public class ReflectionsTest {
	
	@Test
	public void testCreateNewInstanceWithNoDependencies() {
		ObjectWithTwoDependencies object = Reflection.newInstance(ObjectWithTwoDependencies.class, new ArrayList<Object>());
		Assert.assertTrue("object was created", object != null);
		Assert.assertTrue("dependencies were not initialized", object.dependency1 == null && object.dependency2 == null);
	}
	
	@Test
	public void testCreateNewInstanceWithOneDependency() {
		ObjectWithTwoDependencies object = Reflection.newInstance(ObjectWithTwoDependencies.class, Arrays.asList((Object)new Dependency1()));
		Assert.assertTrue("object was created", object != null);
		Assert.assertTrue("dependencies were initialized", Objects.equal(object.dependency1, new Dependency1()) && object.dependency2 == null);
		
		object = Reflection.newInstance(ObjectWithTwoDependencies.class, Arrays.asList((Object)new Dependency2()));
		Assert.assertTrue("object was created", object != null);
		Assert.assertTrue("dependencies were initialized", object.dependency1 == null && Objects.equal(object.dependency2, new Dependency2()));
	}
	
	@Test
	public void testCreateNewInstanceWithTwoDependencies() {
		ObjectWithTwoDependencies object = Reflection.newInstance(ObjectWithTwoDependencies.class, Arrays.asList(new Dependency1(), new Dependency2()));
		Assert.assertTrue("object was created", object != null);
		Assert.assertTrue("dependencies were initialized", Objects.equal(object.dependency1, new Dependency1()) && Objects.equal(object.dependency2, new Dependency2()));
	}
	
	@Test
	public void testCreateNewInstanceWithTwoPrivateSetterDependencies() {
		ObjectWithTwoPrivateSetterDependencies object = Reflection.newInstance(ObjectWithTwoPrivateSetterDependencies.class);
		Assert.assertTrue("object was created", object != null);
		Reflection.injectDependencies(object, ImmutableMap.of("dependency1", new Dependency1(), "dependency2", new Dependency2()));
		Assert.assertTrue("dependencies were initialized correctly", Objects.equal(object.dependency1, new Dependency1()) && Objects.equal(object.dependency2, new Dependency2()));
	}
	
	@Test
	public void testCreateNewInstanceWithTwoPrivateSetterDependenciesButWithOnlyOneCorrectName() {
		ObjectWithTwoPrivateSetterDependencies object = Reflection.newInstance(ObjectWithTwoPrivateSetterDependencies.class);
		Assert.assertTrue("object was created", object != null);
		Reflection.injectDependencies(object, ImmutableMap.of("dependency12", new Dependency1(), "dependency2", new Dependency2()));
		Assert.assertTrue("dependencies were initialized correctly", object.dependency1 == null && Objects.equal(object.dependency2, new Dependency2()));
	}
	
	@Test
	public void testCreateNewInstanceWithTwoDependenciesButOneSetter() {
		ObjectWithTwoDependenciesButOneSetter object = Reflection.newInstance(ObjectWithTwoDependenciesButOneSetter.class);
		Assert.assertTrue("object was created", object != null);
		Reflection.injectDependencies(object, ImmutableMap.of("dependency1", new Dependency1(), "dependency2", new Dependency2()));
		Assert.assertTrue("dependencies were initialized correctly", object.dependency1 == null && Objects.equal(object.dependency2, new Dependency2()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExceptionCreatingNewInstanceWithDependenciesThatDontMatchConstructor() {
		Reflection.newInstance(ObjectWithTwoDependencies.class, Arrays.asList(new Dependency1(), new Dependency3()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExceptionCreatingNewInstanceWithAmbiguousConstructor() {
		Reflection.newInstance(ObjectWithTwoDependenciesAndAmbiguousConstructors.class, Arrays.asList(new Dependency1(), new Dependency2()));
	}
	
	@Test
	public void testResolveGenericParameter() {
		Class<?> genericParameter = Reflection.resolveGenericParameter(ConcreteParameterizedType.class);
		Assert.assertTrue(String.class.equals(genericParameter));
	}
	
	@Test
	public void testResolveGenericParameters() {
		Class<?>[] genericParameters = Reflection.resolveGenericParameters(ConcreteParameterizedType.class);
		Assert.assertTrue(Arrays.equals(genericParameters, new Class<?>[]{ String.class, Integer.class }));
	}
	
	@Test
	public void testResolveGenericParametersWithSubParameters() {
		Class<?>[] genericParameters = Reflection.resolveGenericParameters(ConcreteParameterizedWithSubPararameterizedType.class);
		Assert.assertTrue(Arrays.equals(genericParameters, new Class<?>[]{ String.class, ArrayList.class }));
	}
	
	@Test
	public void testResolveFirstGenericParameterOfType() {
		Class<?> genericParameter = Reflection.resolveFirstGenericParameterOfType(ConcreteParameterizedWithSubPararameterizedType.class, List.class);
		Assert.assertTrue(Objects.equal(genericParameter, ArrayList.class));
	}

}
