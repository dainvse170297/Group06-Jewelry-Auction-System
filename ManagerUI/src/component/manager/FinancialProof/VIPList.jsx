import axios from "axios";
import React, { useEffect, useState } from "react";
import { FaBackward } from "react-icons/fa";
import { Link } from "react-router-dom";
import Sidebar from "../../layout/sidebar/Sidebar";
import "../../home/home.scss";
import Navbar from "../../layout/navbar/Navbar";
import { ToastContainer, toast } from "react-toastify";
import VIPDetails from "./VIPDetails.jsx";
import moment from "moment";

const VIPList = () => {
  // valuationRequests, setValuationRequests

  const [valuationRequests, setValuationRequests] = useState([]);
  const [currentItemsDetail, setCurrentItemsDetail] = useState(null);
  const [selectedStatus, setSelectedStatus] = useState("");
  const [filteredValuationRequests, setFilteredValuationRequests] = useState(
    []
  );

  const [sortOrder, setSortOrder] = useState(""); // Default sort order
  const [sortedRequests, setSortedRequests] = useState([]);
  const sortValuationRequests = (requests) => {
    return requests.sort((a, b) => {
      const dateA = new Date(a.timeRequest);
      const dateB = new Date(b.timeRequest);
      return sortOrder === "newest" ? dateB - dateA : dateA - dateB;
    });
  };

  useEffect(() => {
    // setIsLoading(true)
    const getAll = async () => {
      try {
        axios
          .get("http://localhost:8080/financial-proof/pending-approval")
          .then((result) => {
            setValuationRequests(result.data);
          });
      } catch (error) {
        setErrorMsg("Error fetching data from server");
      }
    };
    getAll();
  }, []);

  const handleDetail = (item) => {
    setCurrentItemsDetail(item);
  };

  useEffect(() => {
    setFilteredValuationRequests(
      valuationRequests.filter(
        (request) => selectedStatus === "" || request.status === selectedStatus
      )
    );
  }, [selectedStatus, valuationRequests]);

  useEffect(() => {
    setSortedRequests(sortValuationRequests(filteredValuationRequests));
  }, [filteredValuationRequests, sortOrder]);
  return (
    <div className="home">
      <Sidebar />
      <ToastContainer />
      <div className="homeContainer">
        <Navbar />
        <div className="ms-5">
          <div className="">
            <Link to={"/staff-function"}>
              <FaBackward />
            </Link>
          </div>

          <div className="col">
            <div className="row">
              <div className="col-sm-7 text-center">
                <h2>VIP Financial Proof Request Details</h2>

                <div className="row">
                  <table className="table">
                    <thead>
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">From</th>
                        <th scope="col">Time</th>
                        <th scope="col">Status</th>
                        <th scope="col">Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      {sortedRequests.map((request, key) => (
                        <>
                          <tr>
                            <th scope="row">{key + 1}</th>
                            <td>Member {request.memberId}</td>
                            <td>
                              {/* {request.timeRequest} */}
                              {moment(request.timeRequest).format(
                                "DD/MM/YYYY HH:mm:ss"
                              )}
                            </td>
                            <td>{request.status}</td>
                            <td>
                              <button
                                onClick={() => handleDetail(request)}
                                className="btn btn-primary"
                                type="button"
                              >
                                Detail
                              </button>
                            </td>
                          </tr>
                        </>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>

              <div className="col-sm-5">
                {currentItemsDetail && (
                  <>
                    <VIPDetails
                      valuationRequest={currentItemsDetail}
                      onHide={() => setCurrentItemsDetail(null)}
                    />
                  </>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default VIPList;
