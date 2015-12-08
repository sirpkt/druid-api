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

package io.druid.data.input;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 */
public class MapBasedInputRow extends MapBasedRow implements InputRow
{
  private final List<String> dimensions;
  private final List<String> floatDimensions;

  public MapBasedInputRow(
      long timestamp,
      List<String> dimensions,
      List<String> floatDimensions,
      Map<String, Object> event
  )
  {
    super(timestamp, event);
    this.dimensions = dimensions;
    this.floatDimensions = floatDimensions;
  }

  public MapBasedInputRow(
      DateTime timestamp,
      List<String> dimensions,
      List<String> floatDimensions,
      Map<String, Object> event
  )
  {
    super(timestamp, event);
    this.dimensions = dimensions;
    this.floatDimensions = floatDimensions;
  }

  @Override
  public List<String> getDimensions()
  {
    List<String> result = new ArrayList<String>(dimensions);
    result.addAll(floatDimensions);
    return result;
  }

  @Override
  public String toString()
  {
    return "MapBasedInputRow{" +
           "timestamp=" + new DateTime(getTimestampFromEpoch()) +
           ", event=" + getEvent() +
           ", dimensions=" + dimensions +
           ", floatDimentions=" + floatDimensions +
           '}';
  }
}
