package org.ros2.rcljava.havelsanros2.models;

import java.util.Arrays;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author asimkaymak
 */
public class Status {

    private long total_capacity;
    private long free_partition_space;
    private long usable_partition_space;
    private List<Boolean> system_live_status;
    private List<Boolean> console_record_status;
    private List<Boolean> display_record_status;

    public Status(long total_capacity, long free_partition_space, long usable_partition_space, Boolean[] system_live_status, Boolean[] console_record_status, Boolean[] display_record_status) {
        this.total_capacity = total_capacity;
        this.free_partition_space = free_partition_space;
        this.usable_partition_space = usable_partition_space;
        this.system_live_status = Arrays.asList(system_live_status);
        this.console_record_status = Arrays.asList(console_record_status);
        this.display_record_status = Arrays.asList(display_record_status);
    }

    public Status() {
    }

    ;

    public long getTotal_capacity() {
        return total_capacity;
    }

    public void setTotal_capacity(long total_capacity) {
        this.total_capacity = total_capacity;
    }

    public long getFree_partition_space() {
        return free_partition_space;
    }

    public void setFree_partition_space(long free_partition_space) {
        this.free_partition_space = free_partition_space;
    }

    public long getUsable_partition_space() {
        return usable_partition_space;
    }

    public void setUsable_partition_space(long usable_partition_space) {
        this.usable_partition_space = usable_partition_space;
    }

    public List<Boolean> getSystem_live_status() {
        return this.system_live_status;
    }

    public void setSystem_live_status(Boolean[] system_live_status) {
        this.system_live_status = Arrays.asList(system_live_status);
    }

    public List<Boolean> getConsole_record_status() {
        return this.console_record_status;
    }

    public void setConsole_record_status(Boolean[] console_record_status) {
        this.console_record_status = Arrays.asList(console_record_status);
    }

    public List<Boolean> getDisplay_record_status() {
        return this.display_record_status;
    }

    public void setDisplay_record_status(Boolean[] display_record_status) {
        this.display_record_status = Arrays.asList(display_record_status);
    }

}
