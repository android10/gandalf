package com.fernandocejas.android10.gandalf.internal;

import com.fernandocejas.android10.gandalf.Gandalf;
import com.fernandocejas.android10.gandalf.joinpoint.GandalfJoinPoint;
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

  public MessageBuilder() {}

  protected String buildTraceEnterMessage(GandalfJoinPoint joinPoint) {

    StringBuilder message = new StringBuilder();
    message.append(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append("Entering");
    message.append(SEPARATOR);
    message.append(METHOD_LABEL);
    message.append(joinPoint.getMethodName());

    message.append("(");
    List<String> methodParamNames = joinPoint.getMethodParamNamesList();
    if (methodParamNames != null && methodParamNames.size() >= 0) {
      for (int i = 0; i < joinPoint.getMethodParamNamesList().size(); i++) {
        message.append(methodParamNames.get(i));
        message.append("=");
        message.append("'");
        message.append(String.valueOf(joinPoint.getMethodParamValuesList().get(i)));
        message.append("'");
        if (!(i == methodParamNames.size() - 1)) {
          message.append(", ");
        }
      }
    }
    message.append(")");

    message.append(SEPARATOR);
    message.append(THREAD_LABEL);
    message.append(joinPoint.getExecutionThreadName());
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }

  protected String buildTraceExitMessage(GandalfJoinPoint joinPoint, Object returnValue,
      String executionTimeMillis) {

    StringBuilder message = new StringBuilder();
    message.append(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append("Exiting");
    message.append(SEPARATOR);
    message.append(METHOD_LABEL);
    message.append(joinPoint.getMethodName());

    //Let's check if there is a return value
    String stringReturnValue = (returnValue != null) ? String.valueOf(returnValue) : null;
    if (stringReturnValue != null && stringReturnValue.trim().length() != 0) {
      message.append(" returning ");
      message.append(stringReturnValue);
    }

    message.append(SEPARATOR);
    message.append(TIME_LABEL);
    message.append(executionTimeMillis);
    message.append(TIME_MILLIS);
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }
}
