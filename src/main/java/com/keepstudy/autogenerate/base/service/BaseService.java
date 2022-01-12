package com.keepstudy.autogenerate.base.service;

import java.util.List;

public interface BaseService<Entry, Key> {

    Entry selectByKey(Key key);

    Entry selectOne(Entry entry);

    List<Entry> selectByEntry(Entry entry);

    Integer selectCount(Entry entry);

    int insertEntry(Entry entry);

    int insertEntryBatch(List<Entry> entryList);

    int updateByKey(Entry entry);

    int deleteByKey(Key key);

    int deleteByEntry(Entry entry);

}
