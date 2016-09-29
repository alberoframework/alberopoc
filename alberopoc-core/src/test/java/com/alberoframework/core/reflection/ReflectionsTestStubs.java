package com.alberoframework.core.reflection;

import java.util.ArrayList;

public class ReflectionsTestStubs {

	public static class ObjectWithTwoDependencies {
		public Dependency1 dependency1;
		public Dependency2 dependency2;
		
		public ObjectWithTwoDependencies() {
		}
		
		public ObjectWithTwoDependencies(Dependency1 dependency1) {
			this.dependency1 = dependency1;
		}
		
		public ObjectWithTwoDependencies(Dependency2 dependency2) {
			this.dependency2 = dependency2;
		}
		
		public ObjectWithTwoDependencies(Dependency1 dependency1, Dependency2 dependency2) {
			this.dependency1 = dependency1;
			this.dependency2 = dependency2;
		}
		
	}
	
	public static class ObjectWithTwoDependenciesAndAmbiguousConstructors {
		public Dependency1 dependency1;
		public Dependency2 dependency2;
		
		public ObjectWithTwoDependenciesAndAmbiguousConstructors() {
		}
		
		public ObjectWithTwoDependenciesAndAmbiguousConstructors(Dependency1 dependency1) {
			this.dependency1 = dependency1;
		}
		
		public ObjectWithTwoDependenciesAndAmbiguousConstructors(Dependency2 dependency2) {
			this.dependency2 = dependency2;
		}
		
		public ObjectWithTwoDependenciesAndAmbiguousConstructors(Dependency1 dependency1, Dependency2 dependency2) {
			this.dependency1 = dependency1;
			this.dependency2 = dependency2;
		}
		
		public ObjectWithTwoDependenciesAndAmbiguousConstructors(Dependency2 dependency2, Dependency1 dependency1) {
			this.dependency1 = dependency1;
			this.dependency2 = dependency2;
		}
		
	}
	
	public static class ObjectWithTwoPrivateSetterDependencies {
		public Dependency1 dependency1;
		public Dependency2 dependency2;
		
		public void setDependency1(Dependency1 dependency1) {
			this.dependency1 = dependency1;
		}
		
		public void setDependency2(Dependency2 dependency2) {
			this.dependency2 = dependency2;
		}
		
	}
	
	public static class ObjectWithTwoDependenciesButOneSetter {
		public Dependency1 dependency1;
		public Dependency2 dependency2;
		
		public void setDependency2(Dependency2 dependency2) {
			this.dependency2 = dependency2;
		}
		
	}
	
	public static class Dependency1 {
		@Override
		public boolean equals(Object obj) {
			return obj != null && obj instanceof Dependency1;
		}
	}
	
	public static class Dependency2 {
		@Override
		public boolean equals(Object obj) {
			return obj != null && obj instanceof Dependency2;
		}
	}
	
	public static class Dependency3 {
		@Override
		public boolean equals(Object obj) {
			return obj != null && obj instanceof Dependency2;
		}
	}
	
	
	
	public class ParameterizedSuperType<T, U> {
		
	}
	
	public class ConcreteParameterizedType extends ParameterizedSuperType<String, Integer> {
		
	}
	
	public class ConcreteParameterizedWithSubPararameterizedType extends ParameterizedSuperType<String, ArrayList<Integer>> {
		
	}
	
}
