package test.com.album.model;

import java.util.List;

public interface AlbumDAO {
	public int insert(AlbumVO vo); // 앨범에 사진을 업로드 하는 매서드
	public int update(AlbumVO vo); // 앨범에 업로드된 정보를 수정하는 매서드
	public int delete(AlbumVO vo); // 앨범에 업로드된 게시물을 삭제하는 매서드
	public int isWriter(AlbumVO vo); // 접속자가 해당 게시글의 작성자인지를 확인하는 매서드
	public AlbumVO selectOne(AlbumVO vo); // 앨범 전체 리스트 중 하나의 게시글의 정보를 불러오는 매서드
	public List<AlbumVO> selectAll(AlbumVO vo, String order); // 앨범 전체 리스트의 정보를 불러오는 매서드 (order값에 따라서 오름/내림차순으로 정렬)
	public List<AlbumVO> searchList(long club_id, String searchKey, String searchWord); // 앨범 전체에 대하여 키워드 검색을 하는 매서드
}
