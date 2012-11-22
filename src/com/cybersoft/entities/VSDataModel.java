package com.cybersoft.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrey
 *         Date: 11/1/12
 *         Time: 3:20 PM
 */
public class VSDataModel {
    private List<PlayedMatch> playedMatchList = new ArrayList<PlayedMatch>();
    private List<FutureMatch> futureMatchList = new ArrayList<FutureMatch>();
    private DateTime serverDateTime;

    public List<PlayedMatch> getPlayedMatchList() {
        return playedMatchList;
    }

    public VSDataModel addFinishedMatch(PlayedMatch match){
        playedMatchList.add(match);
        return this;
    }

    public List<FutureMatch> getFutureMatchList() {
        return futureMatchList;
    }

    public DateTime getServerDateTime() {
        return serverDateTime;
    }

    public void setServerDateTime(DateTime serverDateTime) {
        this.serverDateTime = serverDateTime;
    }
}
