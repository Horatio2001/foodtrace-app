package com.example.foodtrace.dao;

import com.example.foodtrace.entity.CollectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface CollectDao {
    Integer addCreateInfo(@Param("CollectID") String CollectID, @Param("Type") Integer Type
            , @Param("Name") String Name, @Param("GermplasmName") String GermplasmName
            , @Param("GermplasmNameEn") String GermplasmNameEn, @Param("SectionName") String SectionName
            , @Param("GenericName") String GenericName, @Param("ScientificName") String ScientificName
            , @Param("ResourceType") Integer ResourceType, @Param("CollectMethod") Integer CollectMethod
            , @Param("GermplasmSource") String GermplasmSource, @Param("SourceCountry") String SourceCountry
            , @Param("SourceProvince") String SourceProvince, @Param("Source") String Source
            , @Param("SourceOrg") String SourceOrg, @Param("OriginCountry") String OriginCountry
            , @Param("OriginPlace") String OriginPlace, @Param("CollectPlaceLongitude") String CollectPlaceLongitude
            , @Param("CollectPlaceLatitude") String CollectPlaceLatitude, @Param("CollectPlaceAltitude") String CollectPlaceAltitude
            , @Param("CollectPlaceSoilType") Integer CollectPlaceSoilType, @Param("CollectPlaceEcologyType") Integer CollectPlaceEcologyType
            , @Param("CollectMaterialType") Integer CollectMaterialType, @Param("CollectPeople") String CollectPeople
            , @Param("CollectUnit") String CollectUnit, @Param("CollectTime") Date CollectTime
            , @Param("SpeciesName") String SpeciesName, @Param("Image") String Image
            , @Param("CollectRemark") String CollectRemark);

    Integer deleteCollectInfo(@Param("fruitInfoID") String fruitInfoID);

    Integer modifyCollectInfo(@Param("collectInfo") CollectInfo collectInfo);

    CollectInfo queryCollectInfo(@Param("fruitInfoID") String fruitInfoID);

    List<CollectInfo> queryInfosByPage(@Param("pageNum") int pageNum);

    List<CollectInfo> queryDocumentedInfosByPage(@Param("pageNum") int pageNum);

    List<CollectInfo> queryCollectInfosByPage(@Param("pageNum") int pageNum);

    int collectCount();
}

