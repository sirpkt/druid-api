/*
* Licensed to Metamarkets Group Inc. (Metamarkets) under one
* or more contributor license agreements. See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership. Metamarkets licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package io.druid.data.input.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;

public enum DimensionType
{
  STRING("STRING", "java.lang.String"),
  FLOAT("FLOAT", "java.lang.Float");

  private final String name;
  private final Class clazz;

  private DimensionType(String name, String clazz) {
    this.name = name;
    Class loadedClass = null;
    try {
      loadedClass = Class.forName(clazz);
    } catch (ClassNotFoundException e) {
      // However, should not reach here
      e.printStackTrace();
    }
    this.clazz = loadedClass;
  }

  @JsonValue
  @Override
  public String toString()
  {
    return name.toLowerCase();
  }

  @JsonCreator
  public static DimensionType fromString(String name)
  {
    return valueOf(name.toUpperCase());
  }

  public Class getClazz()
  {
    return clazz;
  }

  public Object typeCast(Object o) {
    Object ret = null;
    switch(name) {
      case "STRING":
        ret = String.valueOf(o);
        break;
      case "Float":
        ret = ((Double) o).floatValue();
    }
    return ret;
  }

  public static boolean isValid(String name)
  {
    try {
      DimensionType type = fromString(name);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }
}
