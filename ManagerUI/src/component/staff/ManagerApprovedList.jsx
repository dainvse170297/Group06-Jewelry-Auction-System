import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Button } from 'react-bootstrap'
import { FaBackward } from 'react-icons/fa'
import { Link } from 'react-router-dom'
import Paginator from '../common/Paginator'
import Sidebar from '../layout/sidebar/Sidebar'
import Navbar from '../layout/navbar/Navbar'

export default function ManagerApprovedList() {


    const [ManagerApproval, setManagerApproval] = useState([])
    const [errorMsg, setErrorMsg] = useState('')
    const [currentPage, setCurrentPage] = useState(1)
    const [itemPerPage, setItemPerPage] = useState(5)

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber)
        console.log('New Page:', pageNumber)
    }

    useEffect(() => {
        // setIsLoading(true)
        const getList = async () => {

            try {
                axios.get("http://localhost:8080/valuation/get-all-valuation-manager-approved").then((result) => {
                    setManagerApproval(result.data)
                })

            } catch (error) {
                console.log("Error:", error.message)
                setErrorMsg("Error fetching data from server")
            }
        }
        getList()

    }, [])

    const calculateTotalPage = (itemPerPage, ManagerApproval) => {
        const totalItem = ManagerApproval.length
        return Math.ceil(totalItem / itemPerPage)
    }

    const indexOfLastItem = currentPage * itemPerPage
    const indexOfFirstItem = indexOfLastItem - itemPerPage
    const currentItems = ManagerApproval.slice(indexOfFirstItem, indexOfLastItem)

    return (
        <div className='home'>
            <Sidebar />
            <div className="homeContainer">
                <Navbar />
                <div className='container'>
                    <div className="row">
                        <div className="">
                            <Link to={"/staff-function"}><FaBackward /></Link>
                        </div>
                        {/* {isLoading ? (
                            <>
                                <CircularProgress />
                            </>
                        ) : ( */}
                        <div className="col-lg-3"></div>
                        <div className="col-lg-6">
                            {currentItems.map((request) => (
                                <div className="mb-3 mt-3" key={request.id}>
                                    <div className="card">
                                        <div className="card-body">
                                            <p>Member Id: <strong>{request.memberId}</strong></p>
                                            <p>Description: <strong>{request.description}</strong></p>
                                            <div className="">
                                                <Button className='btn-success'>
                                                    <Link to={`/manager-approved-detail/${request.id}`} style={{ color: 'white', textDecoration: 'none' }}>
                                                        View detail
                                                    </Link>
                                                </Button>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            ))}
                            <div className="flex align-items-center justify-content-center">
                                <Paginator currentPage={currentPage}
                                    totalPages={calculateTotalPage(itemPerPage, ManagerApproval)}
                                    onPageChange={handlePageChange}
                                ></Paginator>
                            </div>
                        </div>
                        <div className="col-lg-3"></div>
                        {/* )} */}
                    </div>
                </div >
            </div > 
        </div>
    )
}

