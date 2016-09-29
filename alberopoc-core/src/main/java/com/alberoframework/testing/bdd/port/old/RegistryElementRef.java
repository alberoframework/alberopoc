package com.alberoframework.testing.bdd.port.old;

import java.util.Optional;

import com.alberoframework.lang.object.BaseObject;
import com.google.common.base.Preconditions;

public class RegistryElementRef<T> extends BaseObject {

  private final Class<T> type;
  private final Optional<String> id;

  public RegistryElementRef(Class<T> type, String id) {
  		Preconditions.checkNotNull(type);
      this.type = type;
      this.id = Optional.ofNullable(id);
  }

  public RegistryElementRef(Class<T> type) {
  		this(type, null);
  }

  public Class<T> type() {
      return type;
  }

  public Optional<String> id() {
      return id;
  }
	
}
