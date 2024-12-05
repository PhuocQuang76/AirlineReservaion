<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function sortTable(columnIndex) {
            var table = $("table");
            var rows = table.find('tr').get().slice(1); // Exclude the header row
            rows.sort(function(a, b) {
                var A = $(a).find('td').eq(columnIndex).text().toUpperCase();
                var B = $(b).find('td').eq(columnIndex).text().toUpperCase();
                return A.localeCompare(B);
            });
            $.each(rows, function(index, row) {
                table.append(row);
            });
        }

        $(document).ready(function() {
            // Your other jQuery code can go here
        });
    </script>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th><a href="javascript:sortTable(0)">Column 1</a></th>
                <th><a href="javascript:sortTable(1)">Column 2</a></th>
                <!-- Add more columns as needed -->
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>2</td>
                <td>2</td>
            </tr>
            <tr>
                <td>1</td>
                <td>3</td>
            </tr>
            <!-- Add more rows as needed -->
        </tbody>
    </table>
</body>
</html>