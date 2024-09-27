package com.mylearning.scheduler.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Task {

    Logger logger = LoggerFactory.getLogger(this.toString());

    // fixedRate => Executes the task at a fixed interval, with the interval being measured from the start of the previous execution.
    // concurrent execution
    @Scheduled(initialDelay = 2000, fixedRate = 5000)
    public void task1() {
        logger.info("task1 -> {}", LocalDateTime.now());
    }

    // fixedDelay ⇒ Executes the task with a fixed delay, where the delay is measured after the completion of the previous execution.
    // once a task completes, then only another task executes with fixedDelay
    // one by one execution
    @Scheduled(fixedDelay = 2000)
    public void task2() {
        logger.info("task2 -> {}", LocalDateTime.now());
    }

    /*
     * ========================================
     *     0 0 10 * * ?: At 10:00 AM every day.
     *     0 0/15 * * * ?: Every 15 minutes.
     *     0 0 0 * * ?: At midnight (00:00) every day.
     *     0 0 12 ? * MON-FRI: At 12:00 PM (noon) Monday through Friday.
     *     0 0 8-10 * * ?: At 8 AM, 9 AM, and 10 AM every day.
     *     0 0 0 1 * ?: At midnight on the 1st day of every month.
     *
     *  =======================================
     *      Second: 0-59
     *      Minute: 0-59
     *      Hour: 0-23
     *      Day of Month: 1-31
     *      Month: 1-12 or JAN-DEC
     *      Day of Week: 0-6 (Sunday=0) or SUN-SAT
     *      ?: No specific value
     *      *: Any value (wildcard)
     *  =========================================
     *
     *
     *      0 * * * * ? => Run a task every minute
     *      Explanation:
     *      0: At the 0th second.
     *      *: Every minute.
     *      *: Every hour.
     *      *: Every day.
     *      *: Every month.
     *      ?: Any day of the week (no specific day of the week).
     *
     *     0 30 10 * * ?  ⇒ Run a task every day at 10:30 AM    *
     *     0:  At the 0th second.
     *     30: At the 30th minute.
     *     10: At 10 AM.
     *     *: Every day.
     *     *: Every month.
     *     ?: Any day of the week (no specific day).
     *
     *     0 0 12 ? * MON ⇒ Run a task every Monday at 12:00 PM
     *     0: At the 0th second.
     *     0: At the 0th minute.
     *     12: At 12 PM (noon).
     *     ?: No specific day of the month.//any random day of the month
     *     *: Every month.
     *     MON: Only on Monday.
     *
     *    0 0 0 1 * ? ⇒ Run a task on the 1st of every month at midnight
     *    0: At the 0th second.
     *    0: At the 0th minute.
     *    0: At midnight (00:00).
     *    1: On the 1st day of the month.
     *    *: Every month.
     *    ?: Any day of the week.
     *
     *
     *
     * */


//
//    Run a task on weekdays (Monday to Friday) at 8:00 AM
//    Cron expression:
//    0 0 8 ? * MON-FRI
//    Explanation:
//            0: At the 0th second.
//            0: At the 0th minute.
//            8: At 8 AM.
//            ?: No specific day of the month.
//            *: Every month.
//            MON-FRI: Monday to Friday.
//    This will run the task at 8:00 AM, Monday through Friday.
//
//   Run a task every 5 minutes
//   Cron expression:
//   0 */5 * * * ?
//    Explanation:
//            0: At the 0th second.
//            */5: Every 5 minutes.
//            *: Every hour.
//            *: Every day.
//            *: Every month.
//            ?: Any day of the week.
//    This will run the task every 5 minutes.
//
//   Run a task on the last day of the month at 11:59 PM
//   Cron expression:
//   0 59 23 L * ?
//    Explanation:
//            0: At the 0th second.
//            59: At the 59th minute.
//            23: At 11 PM (23:00).
//            L: Last day of the month.
//            *: Every month.
//            ?: Any day of the week.
//    This will run the task at 11:59 PM on the last day of the month.
//
//   Run a task on the 15th and 30th of every month at 9:00 AM
//   Cron expression:
//   0 0 9 15,30 * ?
//      Explanation:
//            0: At the 0th second.
//            0: At the 0th minute.
//            9: At 9 AM.
//            15,30: On the 15th and 30th days of the month.
//            *: Every month.
//            ?: Any day of the week.
//    This will run the task at 9:00 AM on the 15th and 30th of every month.

    //  0 */15 * * * ?  => every 15 mins

    // 0/15 0th minute and then every 15 minutes
    //*15 start from 15th sec

    // <second> <minute> <hour> <day-of-month> <month> <day-of-week>
    // Cron expression: Every day at 10 AM
    @Scheduled(cron = "0 0 10 * * ?", zone = "Asia/Kolkata")
    public void task3() {
        logger.info("task3 -> {}", LocalDateTime.now());
    }
}
