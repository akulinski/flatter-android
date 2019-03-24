package com.kpg.flatter.adapters;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;

public class RangeSeekBarAdapter implements OnRangeChangedListener {
    private float lValue, rValue;

    public RangeSeekBarAdapter(float lValue, float rValue) {
        this.lValue = lValue;
        this.rValue = rValue;
    }

    @Override
    public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
        this.lValue = leftValue;
        this.rValue = rightValue;
    }

    @Override
    public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

    }

    @Override
    public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

    }

    public float getlValue() {
        return lValue;
    }

    public void setlValue(float lValue) {
        this.lValue = lValue;
    }

    public float getrValue() {
        return rValue;
    }

    public void setrValue(float rValue) {
        this.rValue = rValue;
    }
}
