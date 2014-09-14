package com.fernandocejas.android10.gandalf;

/**
 * Helper used for common tasks during the library execution.
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

  public static String buildTraceEnterMessage(String threadName, String methodName,
      String[] methodParams, Object[] methodParamValues) {

    StringBuilder message = new StringBuilder();
    message.append(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append("Entering");
    message.append(SEPARATOR);
    message.append(METHOD_LABEL);
    message.append(methodName);

    //Loop through all the method params
    if (methodParams != null
        && methodParams.length != 0
        && methodParamValues != null
        && methodParams.length != methodParamValues.length) {
      message.append("(");
      for (int i = 0; i < methodParams.length; i++) {
        message.append(methodParams[i]);
        message.append("=");
        message.append("'");
        message.append(String.valueOf(methodParamValues[i]));
        message.append("'");
        if (!(i == methodParams.length - 1)) {
          message.append("; ");
        }
      }
      message.append(")");
    }

    message.append(SEPARATOR);
    message.append(THREAD_LABEL);
    message.append(threadName);
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }

  public static String buildTraceExitMessage(String methodName, String returnValue,
      String timeMillis) {

    StringBuilder message = new StringBuilder();
    message.append(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append("Exiting");
    message.append(SEPARATOR);
    message.append(METHOD_LABEL);
    message.append(methodName);

    //Let's check if there is a return value
    if (returnValue != null && returnValue.trim().length() != 0) {
      message.append(" returning ");
      message.append(returnValue);
    }

    message.append(SEPARATOR);
    message.append(TIME_LABEL);
    message.append(timeMillis);
    message.append(TIME_MILLIS);
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }
}
