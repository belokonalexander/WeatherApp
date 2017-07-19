package com.example.alexander.weatherapp.moxy.strategy;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

import java.util.Iterator;
import java.util.List;


public class AddToEndWithCompressor implements StateStrategy {

    private enum StrategyType {
        DO, STOP;
    }

    private String delimiter = "$";

    private String getTag(String incomingTag) {
        return incomingTag.substring(0, incomingTag.lastIndexOf(delimiter));
    }

    private StrategyType getType(String incomingTag) {
        return StrategyType.valueOf(incomingTag.substring(incomingTag.lastIndexOf(delimiter) + 1, incomingTag.length()).toUpperCase());
    }

    @Override
    public <View extends MvpView> void beforeApply(List<ViewCommand<View>> currentState, ViewCommand<View> incomingCommand) {
        Iterator<ViewCommand<View>> iterator = currentState.iterator();


        if (incomingCommand.getStrategyType().equals(AddToEndWithCompressor.class)) {

            String incomingTag = incomingCommand.getTag();
            StrategyType type = getType(incomingTag);
            String tag = getTag(incomingTag);

            while (iterator.hasNext()) {
                ViewCommand<View> entry = iterator.next();
                if (entry.getStrategyType().equals(AddToEndWithCompressor.class) && getTag(entry.getTag()).equals(tag))
                    iterator.remove();

            }

            if (type == StrategyType.DO)
                currentState.add(incomingCommand);
        }

    }

    @Override
    public <View extends MvpView> void afterApply(List<ViewCommand<View>> currentState, ViewCommand<View> incomingCommand) {
        // pass
    }
}
