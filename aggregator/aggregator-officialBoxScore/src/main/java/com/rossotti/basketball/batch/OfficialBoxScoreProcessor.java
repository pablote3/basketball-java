package com.rossotti.basketball.batch;

import com.rossotti.basketball.model.BoxScore;
import org.springframework.batch.item.ItemProcessor;

public class OfficialBoxScoreProcessor implements ItemProcessor<OfficialBoxScore, OfficialBoxScore> {

    @Override
    public OfficialBoxScore process(OfficialBoxScore officialBoxScore) {
        BoxScore.process(officialBoxScore);
        return officialBoxScore;
    }
}
