package com.icia.project.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.DAO.BoardDAO;
import com.icia.project.DAO.CommentDAO;
import com.icia.project.DTO.BoardDTO;
import com.icia.project.DTO.CommentDTO;
import com.icia.project.DTO.PageDTO;

@Service
public class BoardService {
	
	private static final int PAGE_LIMIT = 5;
	private static final int BLOCK_LIMIT = 5;
	
	@Autowired
	private BoardDAO bDAO;
	
	@Autowired
	private CommentDAO cDAO;
	
	private ModelAndView mav;
	
	//게시글 작성
	public ModelAndView boardinsert(BoardDTO bDTO) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile bfile = bDTO.getBfile();
		String bfilename = bfile.getOriginalFilename();
		bfilename = System.currentTimeMillis() + "_" + bfilename;
		String savePath = "C:\\Users\\1\\git\\Spring\\Project\\src\\main\\webapp\\resources\\uploadFile\\"+bfilename;
		if(!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		bDTO.setBfilename(bfilename);
		int result = bDAO.boardinsert(bDTO);
		if(result > 0) {
			mav.setViewName("Main");
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}
	//게시글 리스트 페이징
	public ModelAndView boardlist(int page) {
		mav = new ModelAndView();
		int boardlistcount = bDAO.boardlistcount();
		int startRow = (page-1)*PAGE_LIMIT+1;
		int endRow = page*PAGE_LIMIT;
		
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardlist = bDAO.boardlist(paging);
		int maxPage = (int)(Math.ceil((double)boardlistcount/PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page/BLOCK_LIMIT))) -1) * BLOCK_LIMIT +1;
		                     // Math.ceil = 그냥 반올림
		int endPage = startPage + BLOCK_LIMIT - 1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("boardlist", boardlist);
		mav.setViewName("boardv/BoardList");
		return mav;
	}

	public ModelAndView boardview(int bnumber, int page) {
		mav = new ModelAndView();
		int result = bDAO.boardhits(bnumber);
		List<CommentDTO> commentlist = cDAO.commentlist(bnumber);
		if(result > 0) {
			BoardDTO bDTO = bDAO.boardview(bnumber);
			mav.addObject("boardcomment", commentlist);
			mav.addObject("boardview", bDTO);
			mav.addObject("page", page);
			mav.setViewName("boardv/BoardView");
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}
	//게시글수정 페이지 이동
	public ModelAndView boardupdate(int bnumber, int page) {
		mav = new ModelAndView();
		BoardDTO bDTO = bDAO.boardview(bnumber);
		mav.addObject("boardupdate", bDTO);
		mav.addObject("page", page);
		mav.setViewName("boardv/BoardUpdate");
		return mav;
	}
	//게시글 수정
	public ModelAndView boardupdateform(BoardDTO bDTO, int page, int bnumber) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile bfile = bDTO.getBfile();
		String bfilename = bfile.getOriginalFilename();
		System.out.println("파일이름 : "+bfilename);
		String preexistence = bDTO.getPreexistence();
		if(bfilename.length()==0) {
			bDTO.setBfilename(preexistence);
		} else {
			bfilename = System.currentTimeMillis() + "_" + bfilename;
			String savePath = "C:\\Users\\1\\git\\Spring\\Project\\src\\main\\webapp\\resources\\uploadFile\\"+bfilename;
			if(!bfile.isEmpty()) {
				bfile.transferTo(new File(savePath));
			}
			bDTO.setBfilename(bfilename);
		}
	
		int result = bDAO.boardupdate(bDTO);
		if(result>0) {
			mav.setViewName("redirect:/boardview?bnumber="+bnumber+"&page="+page);
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}

	public ModelAndView boarddelete(int bnumber, int page) {
		mav = new ModelAndView();
		int result = bDAO.boarddelete(bnumber);
		if(result > 0) {
			mav.setViewName("redirect:/boardlist?page="+page);
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}

	public ModelAndView boardsearch(String searchtype, String keyword, int page) {
		mav = new ModelAndView();
		int searchcount = bDAO.searchcount(searchtype, keyword);
		int startRow = (page-1)*PAGE_LIMIT+1; // 1 4 7 10 13 ......
		int endRow = page*PAGE_LIMIT; // 3 6 9 12 ....
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		paging.setKeyword(keyword);
		paging.setSearchtype(searchtype);
		List<BoardDTO> searchlist = bDAO.searchlist(paging);
		int maxPage = (int)(Math.ceil((double)searchcount/PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page/BLOCK_LIMIT))) -1) * BLOCK_LIMIT +1;
		                     // Math.ceil = 그냥 반올림
		int endPage = startPage + BLOCK_LIMIT - 1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("boardlist", searchlist);
		mav.setViewName("boardv/BoardSearchList");
		return mav;
	}

	

}
