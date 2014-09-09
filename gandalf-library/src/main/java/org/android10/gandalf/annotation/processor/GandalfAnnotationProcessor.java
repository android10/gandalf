/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package org.android10.gandalf.annotation.processor;

import java.lang.reflect.Modifier;
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

@SupportedAnnotationTypes("org.android10.gandalf.annotation.DebugTrace")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class GandalfAnnotationProcessor extends AbstractProcessor {

  public GandalfAnnotationProcessor() {
    super();
  }

  @Override public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override public boolean process(Set<? extends TypeElement> typeElements,
      RoundEnvironment roundEnvironment) {

    for (Element element : roundEnvironment.getElementsAnnotatedWith(DebugTrace.class)) {
      if (isValidElement(element)) {
        String methodName = element.getSimpleName().toString();

        try {
          String message = "Debug trace annotation found!!!" + methodName + " in class : "
              + element.getKind().name();

          processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    return true;
  }

  private boolean isValidElement(Element element) {
    return !(element.getModifiers().contains(Modifier.FINAL) ||
        element.getModifiers().contains(Modifier.STATIC));
  }
}
