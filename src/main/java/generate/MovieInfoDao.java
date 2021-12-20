package generate;

import com.chenzx.movie.entity.movie.MovieInfoDo;

public interface MovieInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(MovieInfoDo record);

    int insertSelective(MovieInfoDo record);

    MovieInfoDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MovieInfoDo record);

    int updateByPrimaryKey(MovieInfoDo record);
}
