'use client'

import {useState} from 'react'
// import styles from './styles.module.css'
import Navbar from "@/app/component/navbar/navbar";
import {useRouter} from 'next/router'


export default function Username() {
    // const [mobileMenuOpen, setMobileMenuOpen] = useState(false)
    const router = useRouter();

    return (
            <div className="h-100">
                <Navbar></Navbar>
                <div className="h1">
                    Пользователь %{router.query.username}%
                </div>
            </div>
    )
}