// CClientSocket.cpp: 구현 파일
//

#include "pch.h"
#include "SCREAMMING_SERVER.h"
#include "SCREAMMING_SERVERDlg.h"
#include "CClientSocket.h"
#include "CListenSocket.h"


// CClientSocket

CClientSocket::CClientSocket()
{
}

CClientSocket::~CClientSocket()
{
}

void CClientSocket::SetListenSocket(CAsyncSocket* pSocket)
{
	m_pListenSocket = pSocket;
}

void CClientSocket::OnClose(int nErrorCode)
{
    CSocket::OnClose(nErrorCode);

    CListenSocket* pServerSocket = (CListenSocket*)m_pListenSocket;
    pServerSocket-> CloseClientSocket(this);
}

void CClientSocket::OnReceive(int nErrorCode)
{
    CString strTmp = _T(""), strIPAddress = _T("");
    UINT uPortNumber = 0;
    TCHAR strBuffer[1024];
    ::ZeroMemory(strBuffer, sizeof(strBuffer)); // :: 붙이고 안붙이고 차이 알아보기

    GetPeerName(strIPAddress, uPortNumber);
    if (Receive(strBuffer, sizeof(strBuffer)) > 0) { // 전달된 데이터(문자열)가 있을 경우
        CSCREAMMINGSERVERDlg* pMain = (CSCREAMMINGSERVERDlg*)AfxGetMainWnd();
        strTmp.Format(_T("[%s:%d]: %s"), strIPAddress, uPortNumber, strBuffer);
        pMain->m_MessageList.AddString(strTmp);  // 메시지 리스트(메시지창?)에 입력받은 메시지 띄우기
        pMain->m_MessageList.SetCurSel(pMain->m_MessageList.GetCount() - 1);

        CListenSocket* pServerSocket = (CListenSocket*)m_pListenSocket;
 //       pServerSocket->SendAllMessage(strBuffer); // 다른 클라이언트들에게 메시지 전달
    }

    CSocket::OnReceive(nErrorCode);
}


// CClientSocket 멤버 함수
