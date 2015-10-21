/**
 * Copyright (c) 2010 RedEngine Ltd, http://www.redengine.co.nz. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package com.tomizone.parameters;

/**
 * Throwables that end up pushed into placeholders just give you the name, its nearly
 * always a programming error to pass one as a parameter. If you really want to then
 * {@link Throwable#getMessage()} it first.
 */
@SuppressWarnings("serial")
class ThrowablesCannotBeParametersException
    extends RuntimeException {

  public ThrowablesCannotBeParametersException(Throwable throwable) {
    super(String.format(        "Cannot pass throwables as parameters " +
        "it will just lead to unexpected results. " +
        "If you really want to pass the message as a parameter then " +
        "call getMessage() and pass that.", throwable));
  }

}
