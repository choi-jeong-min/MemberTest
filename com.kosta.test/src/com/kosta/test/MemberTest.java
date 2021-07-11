package com.kosta.test;

import java.util.ArrayList;
import java.util.Scanner;


public class MemberTest {
	public static void main(String[] args) {
  MemberDAO dao = new MemberDAO();
  Scanner sc = new Scanner(System.in);

 while(true) {
 System.out.println("======================================");
 System.out.println("1.입력 |2.수정|3.삭제|4.조회|5.전체보기|6.종료");
 System.out.println("======================================");
 System.out.print("선택> ");

 String choice = sc.nextLine();
 
  if (choice.equals("1")) {
   while(true) {
   System.out.print("아이디를 입력하세요: ");
   String mid = sc.nextLine();

   if(dao.isCheck(mid)) {
   System.out.println("존재하는 아이디입니다.");

   } else {
   System.out.print("패스워드를 입력하세요: ");
   String mpwd = sc.nextLine();

   System.out.print("이름을 입력하세요: ");
   String mname = sc.nextLine();

   System.out.print("이메일을 입력하세요: ");
   String memail = sc.nextLine();

   int result = dao.insert(mid, mpwd, mname, memail);
   if (result >= 1)
   System.out.println("추가 완료");
    else
   System.out.println("추가 실패");
	} 

   System.out.print("\n계속 입력할까요? ");

   String yn = sc.nextLine();
   if(yn.equalsIgnoreCase("n"))
   break;
	}
   
   } else if (choice.equals("2")) {
   while(true) {
   System.out.print("수정할 아이디를 입력하세요: ");
   String mid = sc.nextLine();

   if(dao.isCheck(mid)) {
   System.out.print("수정할 패스워드를 입력하세요: ");
   String mpwd = sc.nextLine();
   System.out.print("수정할 이메일을 입력하세요: ");
    String memail = sc.nextLine();

	int result = dao.update(mid, mpwd, memail);
	if(result >= 1)
	System.out.println("수정 완료");
    else
	System.out.println("수정 실패");
    } else
	System.out.println("존재하지 않는 아이디입니다.");
    System.out.print("\n계속 수정할까요? ");

	String yn = sc.nextLine();
	if(yn.equalsIgnoreCase("n"))
	break;
		}
				
	} else if (choice.equals("3")) {
	while(true) {
	System.out.print("삭제할 아이디를 입력하세요: ");
	String mid = sc.nextLine();

	if(dao.isCheck(mid)) {
	int result = dao.delete(mid);
	if(result >= 1)
	System.out.println("삭제 완료");
    else
    System.out.println("삭제 실패");

    } else
	System.out.println("존재하지 않는 아이디입니다.");

	System.out.print("\n계속 삭제할까요? ");

	String yn = sc.nextLine();
    if(yn.equalsIgnoreCase("n"))
	break;
	}
				
 } else if (choice.equals("4")) {
  while(true) {
	System.out.print("조회할 아이디를 입력하세요: ");
	String mid = sc.nextLine();

  if(dao.isCheck(mid)) {
   System.out.println("-------------------------------------------------------------------");
   System.out.println("번호\t아이디\t패스워드\t이름\t이메일\t\t가입일자");
   System.out.println("-------------------------------------------------------------------");

  dao.select(mid);

  } else
  System.out.println("존재하지 않는 아이디입니다.");

  System.out.print("\n계속 조회할까요? ");

  String yn = sc.nextLine();
  if(yn.equalsIgnoreCase("n"))
	break;
    }

  } else if (choice.equals("5")) {
	while(true) {
	 ArrayList<MemberDTO> list = dao.getAll();

	System.out.println("-------------------------------------------------------------------");
	System.out.println("번호\t아이디\t패스워드\t이름\t이메일\t\t가입일자");
    System.out.println("-------------------------------------------------------------------");

  for(MemberDTO item:list) {
	System.out.println(item.getMno() + "\t" + item.getMid() + "\t" + item.getMpwd() + "\t" 
											+ item.getMname() + "\t" + item.getMemail() + "\t" + item.getMdate());
	}

	break;
	}

 } else if (choice.equals("6")) {
	System.out.println("프로그램 종료");
	break;
	
  } else
	System.out.println("기능을 잘못 입력했습니다.");

		} 

	} 
}s