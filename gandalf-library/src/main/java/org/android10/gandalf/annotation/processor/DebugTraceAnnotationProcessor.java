/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package org.android10.gandalf.annotation.processor;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import org.android10.gandalf.annotation.DebugTrace;

/**
 *
 */
@SupportedAnnotationTypes("org.android10.gandalf.annotation.DebugTrace")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class DebugTraceAnnotationProcessor extends AbstractProcessor {

  public DebugTraceAnnotationProcessor() {
    super();
  }

  @Override public boolean process(Set<? extends TypeElement> typeElements,
      RoundEnvironment roundEnvironment) {

    for (Element element : roundEnvironment.getElementsAnnotatedWith(DebugTrace.class)) {
      String message = "Debug trace annotation found in " + element.getSimpleName();
      processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
    }

    return true;
  }
}
