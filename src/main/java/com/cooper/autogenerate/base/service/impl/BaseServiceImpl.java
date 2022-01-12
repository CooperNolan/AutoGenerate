package com.cooper.autogenerate.base.service.impl;

import com.cooper.autogenerate.base.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class BaseServiceImpl<Entry, Key> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract BaseMapper<Entry, Key> getMapper();

    public Entry selectByKey(Key key) {
        return key == null ? null : getMapper().selectByKey(key);
    }

    public Entry selectOne(Entry entry) {
        return entry == null ? null : getMapper().selectOne(entry);
    }

    public List<Entry> selectByEntry(Entry entry) {
        return entry == null ? null : getMapper().selectByEntry(entry);
    }

    public Integer selectCount(Entry entry) {
        return entry == null ? 0 : getMapper().selectCount(entry);
    }

    public int insertEntry(Entry entry) {
        return entry == null ? 0 : getMapper().insertEntry(entry);
    }

    public int insertEntryBatch(List<Entry> entryList) {
        return entryList == null || entryList.size() < 1 ? 0 : getMapper().insertEntryBatch(entryList);
    }

    public int updateByKey(Entry entry) {
        return entry == null ? 0 : getMapper().updateByKey(entry);
    }

    public int deleteByKey(Key key) {
        return key == null ? 0 : getMapper().deleteByKey(key);
    }

    public int deleteByEntry(Entry entry) {
        return entry == null ? 0 : getMapper().deleteByEntry(entry);
    }

}
