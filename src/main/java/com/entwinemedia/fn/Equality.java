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

import java.util.List;

/** Utility function helping to implement equality. */
public final class Equality {
  private Equality() {
  }

  /** Check if <code>a</code> and <code>b</code> are equal. Each of them may be null. */
  public static boolean eq(Object a, Object b) {
    return (a == b) || (a != null && a.equals(b));
  }

  /** Check if <code>a</code> and <code>b</code> are not equal. Each of them may be null. */
  public static boolean ne(Object a, Object b) {
    return !eq(a, b);
  }

  /** Check if <code>a</code> and <code>b</code> have the same class ({@link Object#getClass()}). Each may be null. */
  public static boolean eqClasses(Object a, Object b) {
    return bothNotNull(a, b) && a.getClass().equals(b.getClass());
  }

  /** Compare the elements of two lists for equality treating the lists as sets. */
  public static boolean eqUnordered(List<?> as, List<?> bs) {
    if (as != null && bs != null && as.size() == bs.size()) {
      for (Object a : as) {
        if (!bs.contains(a))
          return false;
      }
      return true;
    } else {
      return eq(as, bs);
    }
  }

  /** Check if both objects are either null or not null. */
  public static boolean bothNullOrNot(Object a, Object b) {
    return !(a == null ^ b == null);
  }

  public static boolean bothNotNull(Object a, Object b) {
    return a != null && b != null;
  }

  public static boolean bothNull(Object a, Object b) {
    return a == null && b == null;
  }

  /**
   * Create a hash code for a list of objects. Each of them may be null.
   * Algorithm adapted from "Programming in Scala, Second Edition", p670.
   */
  public static int hash(List<Object> as) {
    if (as == null)
      return 0;
    int hash = 0;
    for (Object a : as) {
      if (hash != 0)
        hash = 41 * hash + hash1(a);
      else
        hash = 41 + hash1(a);
    }
    return hash;
  }

  /**
   * Create a hash code for a list of objects. Each of them may be null.
   * Algorithm adapted from "Programming in Scala, Second Edition", p670.
   */
  public static int hash(Object... as) {
    if (as == null)
      return 0;
    int hash = 0;
    for (Object a : as) {
      if (hash != 0)
        hash = 41 * hash + hash1(a);
      else
        hash = 41 + hash1(a);
    }
    return hash;
  }

  private static int hash1(Object a) {
    return a != null ? a.hashCode() : 0;
  }
}
