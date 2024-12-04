import pymysql
from project.database.connect_db import connect_to_db

def execute_query(sql_query):
    try:
        connection = connect_to_db()
        with connection.cursor() as cursor:
            cursor.execute(sql_query)
            result = cursor.fetchall()
        connection.commit()
        return result
    except Exception as e:
        raise ValueError(f"Error executing query: {e}")
    finally:
        connection.close()
