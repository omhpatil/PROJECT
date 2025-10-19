# E-Commerce Production Management System

This project manages the flow of Purchases → Inventory → Production for an e-commerce or manufacturing setup. It allows you to track raw material purchases, manage stock, and handle production orders to create finished goods.

---

## Project Overview

- Purchase → Buy raw materials or components from suppliers.
- Inventory → Track materials you own and can use in production.
- Production → Consume inventory items to create finished goods.
- Production_Items → Links production orders with inventory items used.

---

## STEP 1: Purchase Flow (Buying Materials)

### Endpoints

Method | API | Request | Description
------ | --- | ------- | -----------
GET | /purchases | – | List all purchases
GET | /purchases/:id | – | View a single purchase
POST | /purchases | { purchase object } | Create a new purchase
PUT | /purchases/:id | { purchase object } | Update purchase info
DELETE | /purchases/:id | – | Delete purchase

### Business Meaning

- Each Purchase represents an order to buy raw materials.
- Purchase status:
  1. "requested" → waiting for approval
  2. "approved" → waiting to receive
  3. "received" → added to inventory
- Approved purchases automatically create an inventory record.

### Flow

1. Create a purchase:

POST /purchases
{
  "name": "Iron rods",
  "price": 1200,
  "expected_date": "2025-10-22"
}

Status defaults to "requested".

2. Manager approves purchase:

POST /purchases/:id/status
{
  "status": "approve"
}

Status becomes "approved" and an inventory record is created.

3. Inventory record created automatically:

{
  "purchase_id": 5,
  "status": "available",
  "role": "RAW_MATERIAL",
  "arrive_date": "2025-10-22"
}

---

## STEP 2: Inventory Flow (Managing Stock)

### Endpoints

Method | API | Description
------ | --- | -----------
GET | /inventories | List all inventory items
GET | /inventories/:id | View a specific item
DELETE | /inventories/:id | Remove an inventory item

### Business Meaning

- Inventory tracks materials that are:
  1. "available" → ready to use
  2. "consumed" → already used
  3. "reserved" → assigned to a production order but not yet used
- Each inventory item references the source purchase.

### Flow

1. After purchase approval → inventory item created.
2. Production consumes inventory items.
3. Check stock via /inventories.
4. Remove or discard damaged items.

---

## STEP 3: Production Flow (Making Finished Goods)

### Endpoints

Method | API | Request body
------ | --- | -----------
POST | /productions | { name, date, production_items: [...] }

### Example Request

POST /productions
{
  "name": "Bike Frame Assembly",
  "date": "2025-10-18",
  "production_items": [
    { "inventory_id": 1, "quantity": 3 },
    { "inventory_id": 3, "quantity": 2 }
  ]
}

### Business Meaning

- Start a production order for a finished good.
- Specify required inventory items.
- Each production_item links the production to an inventory_id and quantity used.

### Flow

1. Create production order with production_items.
2. Record added to production_items table.
3. Update inventory status: "available" → "consumed".
4. Optionally, add finished goods to inventory (role = "FINISHED_GOOD").
