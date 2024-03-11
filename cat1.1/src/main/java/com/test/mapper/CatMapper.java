package com.test.mapper;
import com.test.entity.Cat;
import java.util.List;
public interface CatMapper {
    List<Cat> queryAllCats();
}