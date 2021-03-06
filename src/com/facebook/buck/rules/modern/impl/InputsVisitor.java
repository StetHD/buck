/*
 * Copyright 2018-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.rules.modern.impl;

import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.rules.modern.OutputPath;
import java.nio.file.Path;
import java.util.function.Consumer;

/** Enumerates all the referenced SourcePaths. */
public class InputsVisitor extends AbstractValueVisitor<RuntimeException> {
  private final Consumer<SourcePath> inputsBuilder;

  public InputsVisitor(Consumer<SourcePath> inputsBuilder) {
    this.inputsBuilder = inputsBuilder;
  }

  @Override
  public void visitOutputPath(OutputPath value) {}

  @Override
  public void visitSourcePath(SourcePath value) {
    inputsBuilder.accept(value);
  }

  @Override
  public void visitSimple(Object value) {}

  @Override
  public void visitPath(Path path) throws RuntimeException {
    // TODO(cjhopman): Should this require some sort of explicit recognition that the Path doesn't
    // contribute to inputs?
  }
}
