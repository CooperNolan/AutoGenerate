<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="<#if dirType == 1>${generatePackage}.dao.${javaName}Mapper<#else>${generatePackage}.${javaName}.${javaName}Mapper</#if>">

    <sql id="QUERY_COLUMN_LIST">
        <![CDATA[
        <#list fieldInfoList as item >
            ${item.dbName} AS ${item.javaName}<#if item_index + 1 < fieldInfoList?size>,</#if>
        </#list>
        ]]>
    </sql>

    <sql id="QUERY_FROM_TABLE"><![CDATA[FROM ${dbName}]]></sql>

    <sql id="QUERY_WHERE_CLAUSE">
        <where>
            <#list fieldInfoList as item >
            <if test="${item.javaName} != null<#if item.javaType == "String"> and ${item.javaName} != ''</#if>"><![CDATA[AND ${item.dbName} = ${r'#{'}${item.javaName}}]]></if>
            </#list>
        </where>
    </sql>

    <sql id="UPDATE_COLUMN_SET">
        <set>
            <#list fieldInfoList as item >
            <if test="${item.javaName} != null<#if item.javaType == "String"> and ${item.javaName} != ''</#if>"><![CDATA[${item.dbName} = ${r'#{'}${item.javaName}},]]></if>
            </#list>
        </set>
    </sql>

    <select id="selectByKey" parameterType="${primary.javaType}" resultType="${javaName}">
        <![CDATA[SELECT]]>
        <include refid="QUERY_COLUMN_LIST"/>
        <include refid="QUERY_FROM_TABLE"/>
        <![CDATA[WHERE ${primary.dbName} = ${r'#{'}${primary.javaName}}]]>
    </select>

    <select id="selectOne" parameterType="${javaName}" resultType="${javaName}">
        <![CDATA[SELECT]]>
        <include refid="QUERY_COLUMN_LIST"/>
        <include refid="QUERY_FROM_TABLE"/>
        <include refid="QUERY_WHERE_CLAUSE"/>
        <![CDATA[LIMIT 1]]>
    </select>

    <select id="selectByEntry" parameterType="${javaName}" resultType="${javaName}">
        <![CDATA[SELECT]]>
        <include refid="QUERY_COLUMN_LIST"/>
        <include refid="QUERY_FROM_TABLE"/>
        <include refid="QUERY_WHERE_CLAUSE"/>
    </select>

    <select id="selectCount" parameterType="${javaName}" resultType="Integer">
        <![CDATA[SELECT COUNT(${primary.dbName}) AS dataCount]]>
        <include refid="QUERY_FROM_TABLE"/>
        <include refid="QUERY_WHERE_CLAUSE"/>
    </select>

    <insert id="insertEntry" parameterType="${javaName}">
        <![CDATA[
            INSERT INTO ${dbName} (
                <#list fieldInfoList as item >${item.dbName}<#if item_index + 1 < fieldInfoList?size>, </#if></#list>
            ) VALUES (
                <#list fieldInfoList as item >${r'#{'}${item.javaName}, jdbcType=${item.dbType}}<#if item_index + 1 < fieldInfoList?size>, </#if></#list>
            )
        ]]>
    </insert>

    <insert id="insertEntryBatch" parameterType="java.util.List">
        <![CDATA[
            INSERT INTO ${dbName} (
                <#list fieldInfoList as item >${item.dbName}<#if item_index + 1 < fieldInfoList?size>, </#if></#list>
            ) VALUES
        ]]>
        <foreach collection="entryList" item="entry" separator=",">
            <![CDATA[
                (<#list fieldInfoList as item >${r'#{entry.'}${item.javaName}, jdbcType=${item.dbType}}<#if item_index + 1 < fieldInfoList?size>, </#if></#list>)
            ]]>
        </foreach>
    </insert>

    <update id="updateByKey" parameterType="${primary.javaType}">
        <![CDATA[UPDATE ${dbName}]]>
        <include refid="UPDATE_COLUMN_SET"/>
        <![CDATA[WHERE ${primary.dbName} = ${r'#{'}${primary.javaName}}]]>
    </update>

    <delete id="deleteByKey" parameterType="${primary.javaType}">
        <![CDATA[DELETE FROM ${dbName} WHERE ${primary.dbName} = ${r'#{'}${primary.javaName}}]]>
    </delete>

    <delete id="deleteByEntry" parameterType="${javaName}">
        <![CDATA[DELETE FROM ${dbName}]]>
        <include refid="QUERY_WHERE_CLAUSE"/>
    </delete>

</mapper>
