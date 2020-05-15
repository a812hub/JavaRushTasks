package com.javarush.task.task39.task3913.tests;

import com.javarush.task.task39.task3913.Event;
import com.javarush.task.task39.task3913.LogParser;
import com.javarush.task.task39.task3913.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class LogParserTest {
    Path pathDir = Paths.get("C:\\Users\\Asus VivoBook\\IdeaProjects\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs\\");
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    LogParser logParser = new LogParser(pathDir);

    @Test
    public void getNumberOfUniqueIPs_Null_Null() {
        int actual = logParser.getNumberOfUniqueIPs(null, null);
        Assert.assertEquals(5, actual);
    }

    @Test
    public void getUniqueIPs() throws ParseException {
        LogParser logParser = new LogParser(pathDir);
        Date before = sdf.parse("30.08.2012 16:8:40");
        Date after = null;
        Set<String> actual = logParser.getUniqueIPs(after, before);
        Set<String> expected = new HashSet<>(Arrays.asList("192.168.100.2", "127.0.0.1"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getIPsForEvent_Null() throws ParseException {
        Set<String> actual = logParser.getIPsForEvent(Event.SOLVE_TASK, null, null);
        Set<String> expected = new HashSet<>(Arrays.asList("192.168.100.2", "12.12.12.12", "120.120.120.122"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getIPsForStatus() throws ParseException {
        LogParser logParser = new LogParser(pathDir);
        Date before = sdf.parse("31.12.2014 00:00:00");
        Date after = sdf.parse("01.01.2013 00:00:00");
        Set<String> actual = logParser.getIPsForStatus(Status.FAILED, after, before);
        Set<String> expected = new HashSet<>(Arrays.asList("127.0.0.1"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getIPsForStatus_Null() throws ParseException {
        Set<String> actual = logParser.getIPsForStatus(Status.FAILED, null, null);
        Set<String> expected = new HashSet<>(Arrays.asList("127.0.0.1", "146.34.15.5"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getDateWhenUserLoggedFirstTime_Null() {
        Date actual = logParser.getDateWhenUserLoggedFirstTime("Test2", null, null);
        Assert.assertNull(actual);
    }

    @Test
    public void executeTest_get_User_For_Event_DONE_TASK() {
        Set<Object> actual = logParser.execute("get user for event = \"DONE_TASK\"");
        Set<String> expected = new HashSet<>(Arrays.asList("Eduard Petrovich Morozko", "Vasya Pupkin"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void executeTest_get_Event_For_Date_() {
        Set<Object> actual = logParser.execute("get event for date = \"11.12.2013 10:11:12\"");
        Set<Event> expected = new HashSet<>(Arrays.asList(Event.WRITE_MESSAGE));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void executeTest_get_ip_for_user_after_before() {
        Set<Object> actual = logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"");
        Set<String> expected = new HashSet<>(Arrays.asList("127.0.0.1", "146.34.15.5"));
        Assert.assertEquals(expected, actual);
    }
}