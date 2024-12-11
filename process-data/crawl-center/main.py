import json
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from bs4 import BeautifulSoup
import time

options = Options()
options.add_argument("--headless")  
options.add_argument("--no-sandbox")  
options.add_argument("--disable-dev-shm-usage")  

driver = webdriver.Chrome(options=options)

driver.get("https://baohiemtasco.vn/cuu-ho")
time.sleep(3)  

data = []


for page_num in range(1, 45):  
    soup = BeautifulSoup(driver.page_source, 'html.parser')
    
  
    service_cards = soup.find_all('div', class_='rounded-xl')
    for card in service_cards:
        name_tag = card.find('a', class_='font-semibold')
        name = name_tag.text.strip() if name_tag else "N/A"
        
        address_tag = card.find_all('div', class_='line-clamp-2')
        address = address_tag[0].text.strip() if address_tag else "N/A"
        
        phone_tag = card.find_all('div', class_='ant-space-item')[2]
        phone = phone_tag.find('div').text.strip().replace('.', '') if phone_tag else "N/A"
        
    
        entry = {
            "Tên dịch vụ": name,
            "Địa chỉ": address,
            "Số điện thoại": phone
        }
    
        data.append(entry)
    
    try:
        next_button = driver.find_element(By.XPATH, "//li[@title='Next Page']/button")
        if 'aria-disabled' in next_button.get_attribute('outerHTML'):
            break  
        
        next_button.click()
        time.sleep(2)  
    except Exception as e:
        print(f"Error navigating to the next page: {e}")
        break


with open('scraped_data.json', 'w', encoding='utf-8') as json_file:
    json.dump(data, json_file, ensure_ascii=False, indent=4)


driver.quit()

print("Data saved to 'scraped_data.json'")