package com.vmware.eventhub.javasample.Util;

public class Constants {
    public static final String PAYLOAD="{\n" +
            "\t\"eventName\": \"mdm.partymaster.party.create\",\n" +
            "\t\"eventVersion\": 1,\n" +
            "\t\"transactionEntityKeyName\": \"PartyId\",\n" +
            "\t\"transactionEntityKeyValue\": \"5029564800\",\n" +
            "\t\"eventMessage\": {\n" +
            "\t\t\"partyDetails\": [{\n" +
            "\t\t\t\"matchScore\": 100,\n" +
            "\t\t\t\"winningUuid\": \"5029564722\",\n" +
            "\t\t\t\"losingUuid\": [\"5030351275\"],\n" +
            "\t\t\t\"rowidObject\": \"19851276\",\n" +
            "\t\t\t\"creator\": \"mpradeep\",\n" +
            "\t\t\t\"createDate\": \"2021-03-01T09:50:20-08:00\",\n" +
            "\t\t\t\"updatedBy\": \"mpradeep\",\n" +
            "\t\t\t\"lastUpdateDate\": \"2022-02-16T00:43:02.330-08:00\",\n" +
            "\t\t\t\"lastRowidSystem\": \"SYS0\",\n" +
            "\t\t\t\"fullNm\": \"Procter & Gamble Blois\",\n" +
            "\t\t\t\"prtyTyp\": \"Org\",\n" +
            "\t\t\t\"cnfdncCd\": \"0\",\n" +
            "\t\t\t\"uuid\": \"5029564722\",\n" +
            "\t\t\t\"ExSipPop\": \"usa\",\n" +
            "\t\t\t\"StgType\": {\n" +
            "\t\t\t\t\"stgNm\": \"CONVERTED\",\n" +
            "\t\t\t\t\"stgCd\": \"CONVERTED\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"prtySts\": {\n" +
            "\t\t\t\t\"stsCd\": \"ACTIVE\",\n" +
            "\t\t\t\t\"stsNm\": \"ACTIVE\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"CustomerOrgPostalAddress\": {\n" +
            "\t\t\t\t\"item\": [{\n" +
            "\t\t\t\t\t\"addrTyp\": {\n" +
            "\t\t\t\t\t\t\"addrTypDesc\": \"Primary\",\n" +
            "\t\t\t\t\t\t\"addrTyp\": \"Primary\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"rowidObject\": \"16331854\",\n" +
            "\t\t\t\t\t\"PostalAddress\": {\n" +
            "\t\t\t\t\t\t\"rowidObject\": \"16332116\",\n" +
            "\t\t\t\t\t\t\"creator\": \"mpradeep\",\n" +
            "\t\t\t\t\t\t\"createDate\": \"2021-03-01T09:50:20-08:00\",\n" +
            "\t\t\t\t\t\t\"updatedBy\": \"mpradeep\",\n" +
            "\t\t\t\t\t\t\"lastUpdateDate\": \"2022-02-22T05:41:09.452-08:00\",\n" +
            "\t\t\t\t\t\t\"lastRowidSystem\": \"CSP\",\n" +
            "\t\t\t\t\t\t\"addrLn2\": \" \",\n" +
            "\t\t\t\t\t\t\"addrLn1\": \"1 PROCTER AND GAMBLE PLZ\",\n" +
            "\t\t\t\t\t\t\"city\": \"CINCINNATI\",\n" +
            "\t\t\t\t\t\t\"pstlCd\": \"45202\",\n" +
            "\t\t\t\t\t\t\"pstlCdExt\": \"45202-3393\",\n" +
            "\t\t\t\t\t\t\"vldtnMsg\": \"Verified - Input data correct on input but some or all elements were standardised or input contains outdated names or exonyms\",\n" +
            "\t\t\t\t\t\t\"enrchdInd\": \"N\",\n" +
            "\t\t\t\t\t\t\"srcAddrLn1\": \"1 Procter And Gamble Plaza\",\n" +
            "\t\t\t\t\t\t\"srcCity\": \"Cincinnati\",\n" +
            "\t\t\t\t\t\t\"srcPstlCd\": \"45202-3393\",\n" +
            "\t\t\t\t\t\t\"srcState\": \"OH\",\n" +
            "\t\t\t\t\t\t\"srcCntryCd\": \"US\",\n" +
            "\t\t\t\t\t\t\"cntryCd\": {\n" +
            "\t\t\t\t\t\t\t\"cntryCd\": \"US\",\n" +
            "\t\t\t\t\t\t\t\"cntryDesc\": \"United States\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"state\": {\n" +
            "\t\t\t\t\t\t\t\"stateCd\": \"US|OH\",\n" +
            "\t\t\t\t\t\t\t\"stateDesc\": \"Alabama\"\n" +
            "\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\"mlbltyScr\": {\n" +
            "\t\t\t\t\t\t\t\"mlbltyScrTyp\": \"5\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t},\n" +
            "\t\t\t\"ExCustomerOrgRole\": {\n" +
            "\t\t\t\t\"item\": [{\n" +
            "\t\t\t\t\t\"creator\": \"mpradeep\",\n" +
            "\t\t\t\t\t\"createDate\": \"2021-03-01T09:50:20-08:00\",\n" +
            "\t\t\t\t\t\"updatedBy\": \"mpradeep\",\n" +
            "\t\t\t\t\t\"lastUpdateDate\": \"2022-02-05T23:45:38.549-08:00\",\n" +
            "\t\t\t\t\t\"lastRowidSystem\": \"CSP\",\n" +
            "\t\t\t\t\t\"ExRleTyp\": {\n" +
            "\t\t\t\t\t\t\"rleTp\": \"CUST\",\n" +
            "\t\t\t\t\t\t\"rleDesc\": \"CUSTOMER\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t},\n" +
            "\t\t\t\"CustomerOrgIdentifiers\": {\n" +
            "\t\t\t\t\"item\": [{\n" +
            "\t\t\t\t\t\"creator\": \"mpradeep\",\n" +
            "\t\t\t\t\t\"createDate\": \"2021-03-01T09:50:20-08:00\",\n" +
            "\t\t\t\t\t\"updatedBy\": \"mpradeep\",\n" +
            "\t\t\t\t\t\"lastUpdateDate\": \"2022-02-05T23:24:35.957-08:00\",\n" +
            "\t\t\t\t\t\"lastRowidSystem\": \"CSP\",\n" +
            "\t\t\t\t\t\"altIdVal\": \"27a938a9-a314-4206-a7de-7c60151acc2d\",\n" +
            "\t\t\t\t\t\"effStrtDt\": \"2021-03-01T09:50:20-08:00\",\n" +
            "\t\t\t\t\t\"altIdTyp\": {\n" +
            "\t\t\t\t\t\t\"idTyp\": \"ORG_ID\",\n" +
            "\t\t\t\t\t\t\"idTypDesc\": \"ORG_ID\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"stsCd\": {\n" +
            "\t\t\t\t\t\t\"altIdStsDesc\": \"Effective\",\n" +
            "\t\t\t\t\t\t\"altIdStsCd\": \"Effective\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"Ex_SrcInactFlg\": {\n" +
            "\t\t\t\t\t\t\"Ex_SrcInactFlg\": \"NO\",\n" +
            "\t\t\t\t\t\t\"Ex_SrcInactFlgDsc\": \"NO\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}, {\n" +
            "\t\t\t\t\t\"creator\": \"bes_user\",\n" +
            "\t\t\t\t\t\"createDate\": \"2022-02-14T02:58:35.932-08:00\",\n" +
            "\t\t\t\t\t\"updatedBy\": \"admin\",\n" +
            "\t\t\t\t\t\"lastUpdateDate\": \"2022-02-16T08:47:54.254-08:00\",\n" +
            "\t\t\t\t\t\"lastRowidSystem\": \"EBS\",\n" +
            "\t\t\t\t\t\"altIdVal\": \"1000236**14343230118\",\n" +
            "\t\t\t\t\t\"altIdTyp\": {\n" +
            "\t\t\t\t\t\t\"idTyp\": \"CUST_ACCT_ID**CUST_ACCT_SITE_ID\",\n" +
            "\t\t\t\t\t\t\"idTypDesc\": \"CUST_ACCT_ID**CUST_ACCT_SITE_ID\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"Ex_SrcInactFlg\": {\n" +
            "\t\t\t\t\t\t\"Ex_SrcInactFlg\": \"NO\",\n" +
            "\t\t\t\t\t\t\"Ex_SrcInactFlgDsc\": \"NO\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"Ex_SrcSys\": {\n" +
            "\t\t\t\t\t\t\"Ex_SrcSys\": \"SFDC_PARTNER\",\n" +
            "\t\t\t\t\t\t\"Ex_SrcSysDesc\": \"SFDC_PARTNER\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t}\n" +
            "\t\t}],\n" +
            "\t\t\"transactionResult\": {\n" +
            "\t\t\t\"transactionCode\": \"M1020\",\n" +
            "\t\t\t\"transactionMessage\": \"Party record has been found by UUID.\",\n" +
            "\t\t\t\"httpCode\": 200\n" +
            "\t\t},\n" +
            "\t\t\"count\": 1\n" +
            "\t}\n" +
            "}";
}
