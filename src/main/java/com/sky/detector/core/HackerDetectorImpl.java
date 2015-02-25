package com.sky.detector.core;

import java.util.HashMap;

import com.sky.detector.adapter.HackerDetector;
import com.sky.detector.domain.model.Line;
import com.sky.detector.domain.repository.SuspiciousRepository;

public class HackerDetectorImpl implements HackerDetector
{

    private final HashMap<String, SuspiciousRepository> badAttemps = new HashMap<String, SuspiciousRepository>();

    public String parseLine(String logLine)
    {

        Line line = new Line(logLine);

        return retreiveSuspiciousIp(line);
    }

    private String retreiveSuspiciousIp(Line line)
    {
        String result = null;

        if (isIpNotNull(line))
        {
            SuspiciousRepository attemp = new SuspiciousRepository(line);
            if (!isIpStored(line))
            {
                updateRepository(line, attemp);
            }
            else
            {
                result = updateStoredAttemp(line, result);
            }
        }
        return result;
    }

    private String updateStoredAttemp(Line line, String result)
    {
        SuspiciousRepository storedAttemp = badAttemps.get(line.getIp());
        int numAttemps = incrementAttempt(storedAttemp);
        result = updateRepository(line, result, storedAttemp, numAttemps);
        return result;
    }

    private boolean isIpStored(Line line)
    {
        return badAttemps.containsKey(line.getIp());
    }

    private boolean isIpNotNull(Line line)
    {
        return null != line.getIp();
    }

    private int incrementAttempt(SuspiciousRepository storedAttemp)
    {
        int numAttemps = storedAttemp.getAttemps();
        return ++numAttemps;
    }

    private String updateRepository(Line line,
                                    String result,
                                    SuspiciousRepository storedAttemp,
                                    int numAttemps)
    {
        if (numAttemps >= 5)
        {
            result = retreiveIp(line);
        }
        else
        {
            storedAttemp.setAttemps(numAttemps);
            updateRepository(line, storedAttemp);
        }
        return result;
    }

    private void updateRepository(Line line, SuspiciousRepository storedAttemp)
    {
        badAttemps.put(line.getIp(), storedAttemp);
    }

    private String retreiveIp(Line line)
    {
        String result;
        result = line.getIp();
        cleanMemory(line);
        return result;
    }

    private void cleanMemory(Line line)
    {
        badAttemps.remove(line.getIp());
    }
}
