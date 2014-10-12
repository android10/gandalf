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
  private static final String STATS_LABEL = "@Stats -> ";
  private static final String TIME_LABEL = "@Time -> ";
  private static final String SEPARATOR = " :: ";
  private static final String LOG_ENCLOSING_OPEN = "[";
  private static final String LOG_ENCLOSING_CLOSE = "]";
  private static final String TIME_MILLIS = " ms";
  private static final String TIME_NUMBER_SINGULAR = " time";
  private static final String TIME_NUMBER_PLURAL = " times";

  private static final String LABEL_ENTERING = "Entering";
  private static final String LABEL_EXITING = "Exiting";
  private static final String LABEL_RETURNING = " returning ";

  private static final String STATS_LABEL_TOTAL_TIMES_EXECUTED = "Executed ";
  private static final String STATS_LABEL_TOTAL_EXECUTION_TIME = "Total time ";

  protected static final String GANDALF_STATS_MESSAGE_TAG = "GandalfStats";
  protected static final String GANDALF_STATS_MESSAGE_ACTIVITIES_CREATED = "Activities created: ";
  protected static final String GANDALF_STATS_MESSAGE_FRAGMENTS_CREATED = "Fragments created: ";
  protected static final String GANDALF_STATS_MESSAGE_SERVICES_CREATED = "Services created: ";

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

  protected String buildWatchingAspectMessageStats(GandalfJoinPointStats gandalfJoinPointStats) {

    StringBuilder message = new StringBuilder(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append(METHOD_LABEL);
    message.append(gandalfJoinPointStats.getGandalfJoinPoint().getMethodName());
    message.append(buildMethodSignatureWithValues(gandalfJoinPointStats.getGandalfJoinPoint()));
    message.append(SEPARATOR);
    message.append(STATS_LABEL_TOTAL_TIMES_EXECUTED);
    message.append(String.valueOf(gandalfJoinPointStats.getJoinPointTimesExecuted()));
    if (gandalfJoinPointStats.getJoinPointTimesExecuted() == 1) {
      message.append(TIME_NUMBER_SINGULAR);
    } else {
      message.append(TIME_NUMBER_PLURAL);
    }
    message.append(SEPARATOR);
    message.append(STATS_LABEL_TOTAL_EXECUTION_TIME);
    message.append(String.valueOf(gandalfJoinPointStats.getJoinPointTotalExecutionTimeMillis()));
    message.append(TIME_MILLIS);
    message.append(LOG_ENCLOSING_CLOSE);

    return message.toString();
  }

  protected String buildInspectingAspectStatsMessage(String statsLabel, int statsValue) {

    StringBuilder message = new StringBuilder(LIBRARY_LABEL);
    message.append(LOG_ENCLOSING_OPEN);
    message.append(STATS_LABEL);
    message.append(statsLabel);
    message.append(String.valueOf(statsValue));
    message.append(LOG_ENCLOSING_CLOSE);

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
