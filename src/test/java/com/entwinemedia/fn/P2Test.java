/*
 * Copyright 2015 Entwine AG, Switzerland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.entwinemedia.fn;

import static com.entwinemedia.fn.P2.p2;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class P2Test {
  @Test
  public void testEquality() {
    final P2<Integer, String> x = p2(1, "2");
    final P2<Integer, String> y = p2(1, "2");
    final P2<Integer, String> z = p2(1, "2");
    // reflexive
    assertEquals(x, x);
    assertEquals(x, p2(1, "2"));
    // symmetric
    assertEquals(x, y);
    assertEquals(y, x);
    // transitive
    assertEquals(x, y);
    assertEquals(y, z);
    assertEquals(x, z);
    // consistent
    for (int i = 0; i < 10; i++) {
      assertEquals(x, y);
    }
  }
}
