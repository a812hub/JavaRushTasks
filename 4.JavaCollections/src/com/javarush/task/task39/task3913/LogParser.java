package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .map(Log::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.getUser().equals(user))
                .map(Log::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.event == event)
                .map(Log::getIp)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.status.equals(status))
                .map(Log::getIp)
                .collect(Collectors.toSet());
    }

    private List<Log> getAllLogs(Path path) {
        List<Log> result = new ArrayList<>();
        try {
            List<Path> logFiles = Files.list(path)
                    .filter(f -> f.toString().endsWith(".log"))
                    .collect(Collectors.toList());

            for (Path file : logFiles) {
                List<String> logStrings = Files.readAllLines(file);
                for (String logStr : logStrings) {
                    String[] log = logStr.split("\\t");
                    String ip = log[0];
                    String user = log[1];
                    Date date = getDateFromString(log[2]);
                    Event event;
                    int taskNumber = 0;
                    if (log[3].contains(" ")) {
                        event = Event.valueOf(log[3].split(" ")[0]);
                        taskNumber = Integer.parseInt(log[3].split(" ")[1]);
                    } else {
                        event = Event.valueOf(log[3]);
                    }
                    Status status = Status.valueOf(log[4]);
                    result.add(new Log(ip, user, date, event, taskNumber, status));
                }
            }
        } catch (IOException ignored) {
        }
        return result;
    }

    private List<Log> getLogsByDate(Date after, Date before) {
        return getAllLogs(logDir).stream()
                .filter(log -> after == null || !log.date.before(after))
                .filter(log -> before == null || !log.date.after(before))
                .collect(Collectors.toList());
    }

    private Date getDateFromString(String string) {
        if (string == null)
            return null;
        Date date = null;
        SimpleDateFormat[] formats = new SimpleDateFormat[]{
                new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"),
                new SimpleDateFormat("d.M.yyyy H:m:s"),
                new SimpleDateFormat("d.M.y H:m:s"),
                new SimpleDateFormat("d.M.yy H:m:s")};
        for (SimpleDateFormat sdf : formats) {
            try {
                date = sdf.parse(string);
            } catch (ParseException ignored) {
            }
        }
        return date;
    }

    @Override
    public Set<String> getAllUsers() {
        return getAllLogs(logDir).stream()
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .map(Log::getUser)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.user.equals(user))
                .map(Log::getEvent)
                .collect(Collectors.toSet())
                .size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.ip.equals(ip))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.LOGIN))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.DOWNLOAD_PLUGIN))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.WRITE_MESSAGE))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK) && log.taskNumber == task)
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.DONE_TASK))
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.event.equals(Event.DONE_TASK) && log.taskNumber == task)
                .map(Log::getUser)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.user.equals(user))
                .filter(log -> log.event == event)
                .map(Log::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.status == Status.FAILED)
                .map(Log::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.status == Status.ERROR)
                .map(Log::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        try {
            return getLogsByDate(after, before).stream()
                    .filter(log -> log.user.equals(user) && log.event == Event.LOGIN)
                    .map(Log::getDate)
                    .min(Comparator.comparingLong(Date::getTime))
                    .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        try {
            return getLogsByDate(after, before).stream()
                    .filter(log -> log.user.equals(user) && log.event == Event.SOLVE_TASK && log.taskNumber == task)
                    .map(Log::getDate)
                    .min(Comparator.comparingLong(Date::getTime))
                    .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        try {
            return getLogsByDate(after, before).stream()
                    .filter(log -> log.user.equals(user) && log.event == Event.DONE_TASK && log.taskNumber == task)
                    .map(Log::getDate)
                    .min(Comparator.comparingLong(Date::getTime))
                    .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.user.equals(user) && log.event == Event.WRITE_MESSAGE)
                .map(Log::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.user.equals(user) && log.event == Event.DOWNLOAD_PLUGIN)
                .map(Log::getDate)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .map(Log::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.ip.equals(ip))
                .map(Log::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.user.equals(user))
                .map(Log::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.status == Status.FAILED)
                .map(Log::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.status == Status.ERROR)
                .map(Log::getEvent)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        Integer result = getAllSolvedTasksAndTheirNumber(after, before).get(task);
        return result != null ? result : 0;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        Integer result = getAllDoneTasksAndTheirNumber(after, before).get(task);
        return result != null ? result : 0;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.event == Event.SOLVE_TASK)
                .collect(Collectors.toMap(log -> log.taskNumber, count -> 1, Integer::sum));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return getLogsByDate(after, before).stream()
                .filter(log -> log.event == Event.DONE_TASK)
                .collect(Collectors.toMap(log -> log.taskNumber, count -> 1, Integer::sum));
    }

    @Override
    public Set<Object> execute(String query) {
        Pattern pattern = Pattern.compile
                ("get (?<field1>\\w+)( for (?<field2>\\w+) = \"(?<value1>[\\w\\s.:]+)\")?" +
                        "( and date between \"(?<after>[\\d\\s.:]+)\" and \"(?<before>[\\d\\s.:]+)\")?");
        Matcher matcher = pattern.matcher(query);
        matcher.find();
        String field1 = matcher.group("field1");
        String field2 = matcher.group("field2");
        String value1 = matcher.group("value1");
        Date after = getDateFromString(matcher.group("after"));
        Date before = getDateFromString(matcher.group("before"));
        return getAllLogs(logDir).stream()
                .filter(log -> after == null || log.date.before(before))
                .filter(log -> before == null || log.date.after(after))
                .filter(log -> field2 == null || log.getValue(field2).equals(getValue(field2, value1)))
                .map(log -> log.getValue(field1))
                .collect(Collectors.toSet());
    }

    private Object getValue(String field, String value) {
        switch (field) {
            case "ip":
            case "user":
                return value;
            case "date":
                return getDateFromString(value);
            case "event":
                return Event.valueOf(value);
            case "status":
                return Status.valueOf(value);
        }
        return null;
    }

    class Log {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int taskNumber;
        private Status status;

        public Log(String ip, String user, Date date, Event event, int taskNumber, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.taskNumber = taskNumber;
            this.status = status;
        }

        String getIp() {
            return ip;
        }

        String getUser() {
            return user;
        }

        Date getDate() {
            return date;
        }

        Event getEvent() {
            return event;
        }

        int getTaskNumber() {
            return taskNumber;
        }

        Status getStatus() {
            return status;
        }

        Object getValue(String field) {
            switch (field) {
                case "ip":
                    return ip;
                case "user":
                    return user;
                case "date":
                    return date;
                case "event":
                    return event;
                case "status":
                    return status;
            }
            return null;
        }
    }

}