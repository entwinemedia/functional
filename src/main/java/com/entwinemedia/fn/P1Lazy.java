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

import static com.entwinemedia.fn.Equality.eq;

/**
 * Implementation of {@link P1} with lazy evaluation of the encapsulated value {@link P1#get1()}.
 */
public abstract class P1Lazy<A> extends P1<A> {
  public P1Lazy() {
  }

  public static <A> P1<A> p(final A a) {
    return new P1Lazy<A>() {
      @Override public A get1() {
        return a;
      }
    };
  }

  @Override public <B> P1<B> fmap(final Fn<? super A, ? extends B> f) {
    return new P1Lazy<B>() {
      @Override public B get1() {
        return f.apply(P1Lazy.this.get1());
      }
    };
  }

  @Override public <B> P1<B> bind(final Fn<? super A, P1<B>> f) {
    return new P1Lazy<B>() {
      @Override public B get1() {
        return f.apply(P1Lazy.this.get1()).get1();
      }
    };
  }

  /** Evaluate {@link #get1()} and memoize it. */
  public P1<A> memo() {
    final A a = get1();
    return new P1Lazy<A>() {
      @Override public A get1() {
        return a;
      }
    };
  }

  @Override public int hashCode() {
    return get1().hashCode();
  }

  @Override public boolean equals(Object o) {
    return o instanceof P1 && eq(get1(), ((P1) o).get1());
  }
}
