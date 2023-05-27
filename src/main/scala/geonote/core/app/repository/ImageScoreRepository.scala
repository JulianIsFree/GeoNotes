package geonote.core.app.repository

import geonote.core.app.domain.ImageScore
import geonote.core.app.domain.ImageScore.ImageScoreId
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param

import java.util.Optional

trait ImageScoreRepository extends JpaRepository[ImageScore, ImageScoreId] {

  @Query(value = "SELECT url, SUM(score) AS total_score " +
    "FROM image_score " +
    "GROUP BY url " +
    "ORDER BY total_score DESC " +
    "LIMIT 10", nativeQuery = true)
  def findTop10UrlsWithTotalScore: java.util.List[Array[AnyRef]]

  @Query(value = "SELECT * FROM image_score WHERE url = :target_url AND id = :target_id LIMIT 1", nativeQuery = true)
  def findOneByUrlAndId(@Param(value="target_url") url: String, @Param(value="target_id")user: Long): Optional[ImageScore]
}
