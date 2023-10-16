<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"  %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Created</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
            text-align: center;
        }
        .success-message {
            color: #2ecc71;
            font-size: 24px;
            margin-bottom: 20px;
        }
        .product-details {
            font-size: 18px;
        }
        .back-link {
            display: block;
            color: #3498db;
            text-decoration: none;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <p class="success-message">Product Created Successfully!</p>
        <div class="product-details">
            <p><strong>Product Id:</strong>${product.getProductid() }</p>
            <p><strong>Quantity:</strong> ${product.getQuantity() } kWh</p>
            <p><strong>Rate:</strong> ${product.getRate() } Rs/kWh</p>
            <!-- You can display more product details here -->
        </div>
        <a href="${pageContext.request.contextPath}/products" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
