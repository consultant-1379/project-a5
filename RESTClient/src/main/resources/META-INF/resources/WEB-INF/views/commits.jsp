<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
	
	<title>Commits Page</title>

    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="styles/commit-page.css">
    
    <script src="jquery/jquery-1.12.4.js"></script>
    <script src="bootstrap/js/bootstrap.js"></script>
</head>
<body>
    <h3>Commit data (per commit)</h3>
    <table>
        <tr>
            <th>HASH</th>
            <th>TIMESTAMP</th>
            <th>CODE CHURN</th>
            <th>TOTAL REMOVED</th>
            <th>TOTAL ADDED</th>
            <th>TOTAL HUNKS</th>
            <th>CHANGESET SIZE</th>
            <th>FILE INFORMATION</th>
        </tr>
        <c:forEach items="${commits}" var="commit">
            <tr>
                <td>${commit.hash}</td>
                <td>${commit.timestamp}</td>
                <td>${commit.churn}</td>
                <td style="color: red;">${commit.totalRemoved}</td>
                <td style="color: green;">${commit.totalAdded}</td>
                <td>${commit.totalHunks}</td>
                <td>${commit.changedFiles.size()}</td>
                <td><table id="${commit.hash}">
                    <tr>
                        <th>PATH</th>
                        <th>LINES REMOVED</th>
                        <th>LINES ADDED</th>
                        <th>CONTRIBUTOR COUNT</th>
                        <th>MINOR CONTRIBUTOR COUNT</th>
                        <th>LINES BY HIGHEST CONTRIBUTOR</th>
                        <th>HUNKS</th>
                    </tr>
                    <c:forEach items="${commit.changedFiles}" var="file">
                        <tr>
                            <td style="text-align: left;">${file.path}</td>
                            <td style="color: red;">${file.linesRemoved}</td>
                            <td style="color: green;">${file.linesAdded}</td>
                            <td>${file.contributorCount}</td>
                            <td>${file.minorContributorCount}</td>
                            <td>${file.linesByHighestContributor}</td>
                            <td>${file.hunk}</td>
                        </tr>
                    </c:forEach>
                </table></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
