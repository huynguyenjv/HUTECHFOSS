import { faMap } from "@fortawesome/free-regular-svg-icons";
import { faPhoneAlt } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import PropTypes from "prop-types";

export default function ListCenter({ data }) {
    return (
        <main>
            <div className="grid grid-cols-3 gap-9">
                {data.map((item, i) => (
                    <div
                        key={i}
                        className="p-6 transition-shadow duration-300 bg-white border border-gray-200 rounded-lg shadow-lg hover:shadow-xl"
                    >
                        {/* Title spanning across two columns */}
                        <div className="col-span-2 mb-2 text-lg font-semibold text-teal-500">{item.name}</div>

                        {/* Address */}
                        <div className="flex items-center mb-2 text-sm text-gray-500">
                            <FontAwesomeIcon icon={faMap} className="mr-2 text-teal-500" />
                            <span>Địa chỉ: {item.address}</span>
                        </div>

                        {/* Phone Number */}
                        <div className="flex items-center text-sm text-gray-500">
                            <FontAwesomeIcon icon={faPhoneAlt} className="mr-2 text-teal-500" />
                            <span>Số điện thoại: {item.phoneNumber}</span>
                        </div>
                    </div>
                ))}
            </div>
        </main>
    );
}

ListCenter.propTypes = {
    data: PropTypes.array.isRequired,
};
