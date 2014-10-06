package com.fernandocejas.android10.gandalf.internal;

import com.fernandocejas.android10.gandalf.Gandalf;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPoint;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPointStats;
import java.util.List;

/**
 * Class used to build different messages that will be shown in debug mode
 */
public class MessageBuilder {

  private static final String LIBRARY_LABEL = Gandalf.class.getSimpleName() + " => ";
  private static final String METHOD_LABEL = "@Method -> ";
  private static final String THREAD_LABEL = "@Thread -> ";
  private static final String TIME_LABEL = "@Time -> ";
  private static final String SEPARATOR = " :: ";
  private static final String LOG_ENCLOSING_OPEN = "[";
  private static final String LOG_ENCLOSING_CLOSE = "]";
  private static final String TIME_MILLIS = " ms";

  private static final String LABEL_ENTERING = "Entering";
  private static final String LABEL_EXITING = "Exiting";
  private static final String LABEL_RETURNING = " returning ";

  public MessageBuilder() {}

  protected String buildTracingAspectEnterMessage(GandalfJoinPoint joinPoint) {

    StringBuilder message = new StringBuilder(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append(LABEL_ENTERING);
    message.append(SEPARATOR);
    message.append(METHOD_LABEL);
    message.append(joinPoint.getMethodName());
    message.append(buildMethodSignatureWithValues(joinPoint));
    message.append(SEPARATOR);
    message.append(THREAD_LABEL);
    message.append(joinPoint.getExecutionThreadName());
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }

  protected String buildTracingAspectExitMessage(GandalfJoinPoint joinPoint, Object returnValue,
      String executionTimeMillis) {

    StringBuilder message = new StringBuilder(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append(LABEL_EXITING);
    message.append(SEPARATOR);
    message.append(METHOD_LABEL);
    message.append(joinPoint.getMethodName());
    message.append(buildMethodReturningValue(returnValue));
    message.append(SEPARATOR);
    message.append(TIME_LABEL);
    message.append(executionTimeMillis);
    message.append(TIME_MILLIS);
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }

  protected String buildLoggingAspectMessageEverything(GandalfJoinPoint joinPoint,
      Object returnValue,
      String executionTimeMillis) {

    StringBuilder message = new StringBuilder(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append(METHOD_LABEL);
    message.append(joinPoint.getMethodName());
    message.append(buildMethodSignatureWithValues(joinPoint));
    message.append(buildMethodReturningValue(returnValue));
    message.append(SEPARATOR);
    message.append(THREAD_LABEL);
    message.append(joinPoint.getExecutionThreadName());
    message.append(SEPARATOR);
    message.append(TIME_LABEL);
    message.append(executionTimeMillis);
    message.append(TIME_MILLIS);
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }

  protected String buildLoggingAspectMessageSignature(GandalfJoinPoint joinPoint,
      Object returnValue) {

    StringBuilder message = new StringBuilder(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append(METHOD_LABEL);
    message.append(joinPoint.getMethodName());
    message.append(buildMethodSignatureWithValues(joinPoint));
    message.append(buildMethodReturningValue(returnValue));
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }

  protected String buildLoggingAspectMessageThread(GandalfJoinPoint joinPoint) {

    StringBuilder message = new StringBuilder(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append(METHOD_LABEL);
    message.append(joinPoint.getMethodName());
    message.append(buildMethodSignatureWithValues(joinPoint));
    message.append(SEPARATOR);
    message.append(THREAD_LABEL);
    message.append(joinPoint.getExecutionThreadName());
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }

  protected String buildLoggingAspectMessageTime(GandalfJoinPoint joinPoint,
      String executionTimeMillis) {

    StringBuilder message = new StringBuilder(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append(METHOD_LABEL);
    message.append(joinPoint.getMethodName());
    message.append(buildMethodSignatureWithValues(joinPoint));
    message.append(SEPARATOR);
    message.append(TIME_LABEL);
    message.append(executionTimeMillis);
    message.append(TIME_MILLIS);
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }

  protected String buildTrackingAspectMessageStats(GandalfJoinPointStats gandalfJoinPointStats) {
    StringBuilder message = new StringBuilder(LIBRARY_LABEL);
    return message.toString();
  }

  private String buildMethodSignatureWithValues(GandalfJoinPoint joinPoint) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("(");
    List<String> methodParamNames = joinPoint.getMethodParamNamesList();
    if (methodParamNames != null && !methodParamNames.isEmpty()) {
      for (int i = 0; i < joinPoint.getMethodParamNamesList().size(); i++) {
        stringBuilder.append(methodParamNames.get(i));
        stringBuilder.append("=");
        stringBuilder.append("'");
        stringBuilder.append(String.valueOf(joinPoint.getMethodParamValuesList().get(i)));
        stringBuilder.append("'");
        if ((i != methodParamNames.size() - 1)) {
          stringBuilder.append(", ");
        }
      }
    }
    stringBuilder.append(")");

    return stringBuilder.toString();
  }

  private String buildMethodReturningValue(Object returnValue) {
    StringBuilder stringBuilder = new StringBuilder();
    String stringReturnValue = (returnValue != null) ? String.valueOf(returnValue) : null;
    if (stringReturnValue != null && stringReturnValue.trim().length() != 0) {
      stringBuilder.append(LABEL_RETURNING);
      stringBuilder.append(stringReturnValue);
    }

    return stringBuilder.toString();
  }
}
