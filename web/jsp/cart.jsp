<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Bookshop</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>

    <body>
        <jsp:include page="header.jsp" />

        <h1>The following items are in your shopping cart</h1>

        <form name="form1" method="post" action="./books">
            <input type="hidden" name="action" value="update_cart">

            <table>
                <thead>
                    <tr>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Price/unit</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                        <th>Remove</th>
                    </tr>
                </thead>

                <tbody>
                    <c:set var="totalCostOfOrder" value="0" />

                    <c:forEach var="entry" items="${cart}">
                        <tr>
                            <td>${entry.key}</td>
                            <td>${entry.value.book.title}</td>
                            <td>${entry.value.book.dollarPrice}</td>
                            <td>
                                <input type="text"
                                       name="${entry.key}"
                                       size="2"
                                       value="${entry.value.quantity}"
                                       maxlength="4">
                            </td>
                            <td>${entry.value.dollarOrderCost}</td>
                            <td>
                                <div align="center">
                                    <input type="checkbox"
                                           name="remove"
                                           value="${entry.key}">
                                </div>
                            </td>
                        </tr>

                        <c:set var="totalCostOfOrder"
                               value="${totalCostOfOrder + entry.value.orderCost}" />
                    </c:forEach>

                    <fmt:formatNumber var="totalOrderInDollars"
                                      value="${totalCostOfOrder}"
                                      minFractionDigits="2"
                                      maxFractionDigits="2" />

                    <tr>
                        <td colspan="4">
                            <input type="submit" name="Submit" value="Update Cart">
                        </td>
                        <td colspan="2">
                            <div align="right">
                                <b>ORDER TOTAL $${totalOrderInDollars}</b>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>

        <div class="link-container">
            <p><a href="./books?action=continue">Continue Shopping</a></p>
            <p><a href="./books?action=checkout">Check Out</a></p>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>