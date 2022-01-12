package com.keepstudy.generate;

import com.keepstudy.config.GenerateConfig;
import com.keepstudy.domain.TableInfo;
import freemarker.template.Configuration;

public interface GenerateStrategy {

    void createTemplate(GenerateConfig config, TableInfo tableInfo, Configuration cfg);

}
