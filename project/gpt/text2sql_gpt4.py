import openai
from project.settings import OPENAI_API_KEY

# Thiết lập API key
openai.api_key = OPENAI_API_KEY

def generate_sql(user_query):
    prompt = f"""
    Bạn là một trợ lý AI chuyên hỗ trợ Text2SQL.
    Các bảng trong cơ sở dữ liệu:
    - provinces(id, name, region, population, disaster_level, relief_priority)
    - essential_goods(id, name, category, unit, current_stock)
    - suppliers(id, name, location)
    - vehicles(id, type, capacity, status)
    - supplier_goods(id, supplier_id, good_id, quantity_available)
    - supply_routes(id, province_id, supplier_id, vehicle_id, good_id, quantity, distance_km, estimated_time_hours, delay_reason, priority_level, cost, status)

    Câu hỏi của người dùng: "{user_query}"

    Hãy chỉ trả về câu truy vấn SQL dưới dạng văn bản.
    """

    # Gọi API OpenAI để sinh câu SQL
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "You are a Text2SQL assistant."},
            {"role": "user", "content": prompt},
        ],
        temperature=0,
    )

    # Trả về câu SQL
    sql_query = response.choices[0].message['content']
    return sql_query.strip()
