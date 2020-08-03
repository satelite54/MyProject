
// SCREAMMING_SERVERDlg.h: 헤더 파일
//

#pragma once
#include "CListenSocket.h"


// CSCREAMMINGSERVERDlg 대화 상자
class CSCREAMMINGSERVERDlg : public CDialogEx
{
// 생성입니다.
public:
	CSCREAMMINGSERVERDlg(CWnd* pParent = nullptr);	// 표준 생성자입니다.
	CListenSocket m_ListenSocket;
	CListBox* m_clientList;
// 대화 상자 데이터입니다.
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_SCREAMMING_SERVER_DIALOG };
#endif

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV 지원입니다.


// 구현입니다.
protected:
	HICON m_hIcon;

	// 생성된 메시지 맵 함수
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	void OnDestroy();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()
public:
	CListBox m_MessageList;
};
